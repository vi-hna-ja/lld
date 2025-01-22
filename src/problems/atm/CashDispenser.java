package problems.atm;

import problems.atm.models.InsufficientBalanceException;

public class CashDispenser {

    public double amount;
    public CashDispenser(double amount) {
        this.amount = amount;
    }

    public synchronized void dispenseCash(double amount) {
        try {
            if (this.amount < amount) {
                throw new InsufficientBalanceException(amount);
            }
            this.amount -= amount;
        } catch (InsufficientBalanceException e) {
            throw new RuntimeException();
        }
    }

}
