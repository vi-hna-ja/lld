package problems.amazonlocker.services;

import java.util.UUID;

public class Utils {

    public static String generateId(String prefix) {
        return prefix.toUpperCase() + UUID.randomUUID().toString().substring(0, 5);
    }

    public static String generatePin() {
        return "P" + UUID.randomUUID().toString().substring(0, 3);
    }

}
