package problems.auction.services;

import problems.auction.models.User;
import problems.auction.models.UserLoginRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserRepository {
    private ConcurrentHashMap<String, User> users;
    private Map<String, String> emailToUserIdMap;
    private Map<String, Integer> invalidLoginAttemptsCounter;
    private Lock lock;
    public UserRepository() {
        this.users = new ConcurrentHashMap<>();
        this.emailToUserIdMap = new HashMap<>();
        this.invalidLoginAttemptsCounter = new HashMap<>();
        this.lock = new ReentrantLock();
    }

    public void createUser(User user) {
        lock.lock();
        try {
            String userId = user.getUserId();
            String emailId = user.getEmail();
            if (users.containsKey(userId) || emailToUserIdMap.containsKey(emailId)) {
                System.out.printf("Email %s already exists - %s\n", emailId, userId);
                return;
            }

            users.put(userId, user);
            emailToUserIdMap.put(emailId, userId);
        } finally {
            lock.unlock();
        }
    }

    public void loginUser(User user, UserLoginRequest loginRequest) {
        lock.lock();
        try {
            String emailId = loginRequest.getEmail();
            if (invalidLoginAttemptsCounter.getOrDefault(emailId, 0) >= 3) {
                System.out.printf("[%s] Maximum invalid login attempts reached. User blocked till 1 hr\n", emailId);
                user.setLoginBlocked(true);
                user.setBlockedUntil(new Random().nextLong(60));
                users.put(user.getUserId(), user);
            }
            else {
                if (user.isLoginBlocked()) {
                    int currentTime = new Random().nextInt(61);
                    if (user.getBlockedUntil() < currentTime) {
                        System.out.printf("[%s] Login block has lifted\n", user.getUserId());
                        user.setBlockedUntil(null);
                        user.setLoginBlocked(false);
                        users.put(user.getUserId(), user);
                    }
                }


                if (loginRequest.getPassword().equals(user.getPassword())) {
                    System.out.printf("User %s login successful\n", user.getUserId());
                } else {
                    System.out.printf("User %s login unsuccessful\n", user.getUserId());
                    invalidLoginAttemptsCounter.put(emailId, invalidLoginAttemptsCounter.getOrDefault(emailId, 0) + 1);
                }
            }
        } finally {
            lock.unlock();;
        }
    }

    public Optional<User> getUserByEmail(String email) {
        if (!emailToUserIdMap.containsKey(email)) {
            return Optional.empty();
        }
        else {
            String userId = emailToUserIdMap.get(email);
            return Optional.of(users.get(userId));
        }
    }
}
