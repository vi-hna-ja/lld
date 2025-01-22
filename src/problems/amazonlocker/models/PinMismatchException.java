package problems.amazonlocker.models;

public class PinMismatchException extends Exception{
    public PinMismatchException() {
        super("Pin mismatch. Please enter valid pin");
    }
}
