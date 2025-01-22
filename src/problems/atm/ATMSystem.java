package problems.atm;

import problems.atm.models.BankAccount;
import problems.atm.models.Card;
import problems.atm.models.User;

public class ATMSystem {

    private static ATMSystem atmSystem;
    private CashDispenser cashDispenser;
    private BankService bankService;

    private ATMSystem(double amount) {
        this.cashDispenser = new CashDispenser(amount);
        this.bankService = new BankService();
    }

    public synchronized static ATMSystem getAtmSystemInstance(double amount) {
        if (atmSystem == null) {
            synchronized (ATMSystem.class) {
                if (atmSystem == null) {
                    atmSystem = new ATMSystem(amount);
                }
            }
        }
        return atmSystem;
    }

    double checkBalance(User user, Card card) {
        return bankService.checkBalance(user, card);
    }

    void withdrawCash(User user, Card card) {
        bankService.withdrawCash(user, card);
    }

    void depositCash(User user, BankAccount account) {
        bankService.depositCash(user, account);
    }

    User createUser(String name) {
        return bankService.createUser(name);
    }

    Card addCard(String cardNumber, String bankAccountNumber) {
        return bankService.addCard(cardNumber, bankAccountNumber);
    }

    BankAccount createBankAccount(String userId) {
        return bankService.createBankAccount(userId);
    }

}
