package problems.atm.models;

import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private final String bankAccountNumber;
    private double accountBalance;
    private final String userId;

    public BankAccount(String userId) {
        this.bankAccountNumber = "BA" + UUID.randomUUID().toString().substring(0, 10);
        this.userId = userId;
        this.accountBalance = 0;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public String getUserId() {
        return userId;
    }

    public void depositAmount(double amount, ReentrantLock lock) {
        lock.lock();
        try {
            this.accountBalance += amount;
        }
        finally {
            lock.unlock();
        }
    }

    public void withdrawAmount(double amount, ReentrantLock lock){
        lock.lock();
        try {
            if (accountBalance < amount) {
                throw new InsufficientBalanceException(amount);
            }
            this.accountBalance -= amount;
        }
        catch (InsufficientBalanceException e) {
            throw new RuntimeException();
        }
        finally {
            lock.unlock();
        }
    }

}
