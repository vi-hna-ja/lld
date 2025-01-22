package problems.atm;

import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import problems.atm.models.BankAccount;
import problems.atm.models.Card;
import problems.atm.models.PinMismatchException;
import problems.atm.models.User;

public class BankService {
    private final ConcurrentHashMap<String, User> users;
    private final ConcurrentHashMap<String, BankAccount> bankAccounts;
    private final ConcurrentHashMap<String, Card> cards;
    private final ReentrantLock lock;
    private final Scanner scanner;

    public BankService() {
        this.users = new ConcurrentHashMap<>();
        this.bankAccounts = new ConcurrentHashMap<>();
        this.cards = new ConcurrentHashMap<>();
        this.lock = new ReentrantLock();
        this.scanner = new Scanner(System.in);
    }

    User createUser(String name) {
        User user = new User(name);
        users.put(user.getUserId(), user);
        return user;
    }

    Card addCard(String cardNumber, String bankAccountNumber) {
        Card card = new Card(cardNumber, bankAccountNumber);
        cards.put(card.getCardNumber(), card);
        return card;
    }

    BankAccount createBankAccount(String userId) {
        BankAccount bankAccount = new BankAccount(userId);
        bankAccounts.put(bankAccount.getBankAccountNumber(), bankAccount);
        return bankAccount;
    }

    synchronized void validatePin(String pinToValidate) {
        try {
            System.out.print("Please enter your 4 digit pin- \n");
            String pin = scanner.next();
            if (!pin.equals(pinToValidate))
                throw new PinMismatchException();
        } catch (PinMismatchException e) {
            throw new RuntimeException();
        }
    }

    double checkBalance(User user, Card card) {
        System.out.printf("Checking balance on card %s of user %s\n", card.getCardNumber(), user.getName());
        String bankAccountNumber = card.getBankAccountNumber();
        return bankAccounts.get(bankAccountNumber).getAccountBalance();
    }

    synchronized void withdrawCash(User user, Card card) {
        lock.lock();
        try {
            validatePin(card.getPin());
            String bankAccountNumber = card.getBankAccountNumber();
            double amountToWithdraw = scanner.nextDouble();
            System.out.printf("Withdrawing amount %s from account %s using card %s by user %s\n",
                    amountToWithdraw, bankAccountNumber, card.getCardNumber(), user.getName());
            BankAccount bankAccount = bankAccounts.get(bankAccountNumber);
            bankAccount.withdrawAmount(amountToWithdraw, lock);

            System.out.printf("Available balance now %s\n", bankAccount.getAccountBalance());
        } finally {
            lock.unlock();
        }
    }

    void depositCash(User user, BankAccount bankAccount) {
        lock.lock();
        try {
            double amountToDeposit = scanner.nextDouble();
            System.out.printf("Deposit amount %s from account %s by user %s\n",
                    amountToDeposit, bankAccount.getBankAccountNumber(), user.getName());
            bankAccount.depositAmount(amountToDeposit, lock);

            System.out.printf("Available balance now %s\n", bankAccount.getAccountBalance());
        } finally {
            lock.unlock();
        }
    }
}
