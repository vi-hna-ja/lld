package problems.atm.models;

public class PinMismatchException extends Exception {
    public PinMismatchException() {
        super("Incorrect pin\n");
    }
}
