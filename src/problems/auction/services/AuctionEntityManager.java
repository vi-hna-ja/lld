package problems.auction.services;

import problems.auction.models.AuctionEntity;
import problems.auction.models.AuctionEntityState;
import problems.auction.models.BidRequest;
import problems.auction.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AuctionEntityManager {
    private ConcurrentHashMap<String, AuctionEntity> auctionEntities;
    private ConcurrentHashMap<String, List<User>> auctionEntityToCurrentUsers;
    private UserRepository userRepository;
    private Lock lock;

    public AuctionEntityManager() {
        this.auctionEntities = new ConcurrentHashMap<>();
        this.auctionEntityToCurrentUsers = new ConcurrentHashMap<>();
        this.userRepository = new UserRepository();
        this.lock = new ReentrantLock();
    }

    public void createAuctionListing(AuctionEntity auctionEntity) {
        lock.lock();
        try {
            String id = auctionEntity.getEntityId();
            if (auctionEntities.containsKey(id)) {
                System.out.printf("Entity %s already exists\n", id);
            } else {
                auctionEntities.put(id, auctionEntity);
            }
        } finally {
            lock.unlock();
        }
    }

    public void bid(BidRequest bidRequest) {
        lock.lock();
        try {
            String email = bidRequest.getEmailId();
            String entityId = bidRequest.getEntityId();
            Optional<User> userOptional = userRepository.getUserByEmail(email);
            if (userOptional.isEmpty()) {
                System.out.printf("Invalid email %s. Cannot proceed bidding\n", email);
                return;
            }
            if (!auctionEntities.containsKey(entityId)) {
                System.out.printf("Invalid item %s. Cannot proceed bidding\n", entityId);
                return;
            }

            User user = userOptional.get();
//            auctionEntityToCurrentUsers.putIfAbsent(entityId, d -> new ArrayList<User>()).add(user);

            AuctionEntity entity = auctionEntities.get(entityId);
            if (entity.getState() == AuctionEntityState.SOLD) {
                System.out.printf("Cannot perform auction on an already sold item %s\n", entityId);
                return;
            }

            Lock auctionLock = new ReentrantLock();
            auctionLock.lock();
            try {
                int biddingPrice = bidRequest.getPrice();

                // move state to BUY
                if (entity.getState() != AuctionEntityState.BUY_IN_PROGRESS) {
                    entity.setState(AuctionEntityState.BUY_IN_PROGRESS);
                    auctionEntities.put(entityId, entity);
                }

                if (entity.getCurrentPrice() < biddingPrice) {
                    entity.setCurrentPrice(biddingPrice);
                    entity.setBuyerId(user.getUserId());
                    auctionEntities.put(entityId, entity);

                    // notify users
                    auctionEntityToCurrentUsers.get(entityId)
                            .stream().forEach(u -> u.notify(entityId, biddingPrice));
                }


                int currentTime = new Random().nextInt(13);
                if (entity.getBiddingDuration() <= currentTime) {
                    System.out.printf("Auction for the item %s has ended\n", entityId);
                    System.out.printf("It is sold to user %s for price %s\n", user.getUserId(), biddingPrice);

                    // notify users
                    auctionEntityToCurrentUsers.get(entityId)
                            .stream().forEach(u -> u.notify(entityId, biddingPrice));

                    // update state
                    entity.setState(AuctionEntityState.SOLD);
                    auctionEntities.put(entityId, entity);
                }
            } finally {
                auctionLock.unlock();
            }
        } finally {
            lock.unlock();
        }
    }
}
