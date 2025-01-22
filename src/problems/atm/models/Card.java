package problems.atm.models;

import java.util.UUID;

public class Card {
    private final String cardNumber;
    private final String pin;
    private final String bankAccountNumber;

    public Card(String cardNumber, String bankAccountNumber) {
        this.cardNumber = cardNumber;
        this.bankAccountNumber = bankAccountNumber;
        this.pin = UUID.randomUUID().toString().substring(0, 3);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }
}
