package problems.atm.models;

public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(double amount) {
        super(String.format("Not enough funds to transfer amount: %s\n", amount));
    }
}
