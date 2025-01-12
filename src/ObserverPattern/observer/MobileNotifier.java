package ObserverPattern.observer;

public class MobileNotifier implements Notifier {

    String phoneNumber;

    public MobileNotifier(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void update() {
        // update user on mobile
        System.out.println("Updating user on phone: " + phoneNumber);
    }
}
