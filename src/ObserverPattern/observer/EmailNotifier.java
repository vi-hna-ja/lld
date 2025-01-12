package ObserverPattern.observer;

public class EmailNotifier implements Notifier {

    String email;

    public EmailNotifier(String email) {
        this.email = email;
    }

    @Override
    public void update() {
        // update user on email
        System.out.println("Updating user on email: " + email);
    }
}
