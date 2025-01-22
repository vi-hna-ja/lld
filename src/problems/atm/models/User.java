package problems.atm.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class User {
    private final String name;
    private final String userId;
    private final List<String> bankAccounts;
    private final List<String> cards;

    public User(String name) {
        this.name = name;
        this.userId = "U" + UUID.randomUUID().toString().substring(0, 5);
        this.bankAccounts = new ArrayList<>();
        this.cards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public List<String> getBankAccounts() {
        return bankAccounts;
    }

    public List<String> getCards() {
        return cards;
    }
}

