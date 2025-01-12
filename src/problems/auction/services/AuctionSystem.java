package problems.auction.services;

import problems.auction.models.AuctionEntity;
import problems.auction.models.BidRequest;
import problems.auction.models.User;
import problems.auction.models.UserLoginRequest;

import java.util.Optional;
import java.util.UUID;

public class AuctionSystem {
    private AuctionEntityManager auctionEntityManager;
    private UserRepository userRepository;
    public AuctionSystem() {
        this.auctionEntityManager = new AuctionEntityManager();
        this.userRepository = new UserRepository();
    }

    public void registerUser(String email, String password) {
        String userId = UUID.randomUUID().toString();
        User user = new User(userId, email, password);
        userRepository.createUser(user);
    }

    public void loginUser(String email, String password) {
        Optional<User> userOptional = userRepository.getUserByEmail(email);
        if (userOptional.isEmpty()) {
            System.out.printf("Invalid email %s. Cannot proceed bidding\n", email);
            return;
        }


        userRepository.loginUser(userOptional.get(), new UserLoginRequest(email, password));
    }

    public void createAuctionItem(String buyerId, String name, String desc, int startingPrice, int duration) {
        String entityId = UUID.randomUUID().toString();
        AuctionEntity entity = new AuctionEntity(entityId, name, desc, startingPrice, duration, buyerId);
        auctionEntityManager.createAuctionListing(entity);
    }

    public void bid(BidRequest request) {
        auctionEntityManager.bid(request);
    }
}
