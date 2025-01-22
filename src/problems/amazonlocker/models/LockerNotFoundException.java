package problems.amazonlocker.models;

public class LockerNotFoundException extends Exception{
    public LockerNotFoundException() {
        super("Could not find an empty locker. Please try again later.");
    }
}
