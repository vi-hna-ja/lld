package problems.hotelbooking.services;

import java.util.UUID;

public class Utils {
    public static String generateId(String prefix) {
        return prefix + UUID.randomUUID().toString().substring(0, 8);
    }
}
