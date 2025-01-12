package problems.pubsub.subscribers;

public class Subscriber {

    private final String email;

    public Subscriber(String email) {
        this.email = email;
    }

    public void update(String news) {
        System.out.printf("I, %s, received the news %s\n", getEmail(), news);
    }

    public String getEmail() {
        return email;
    }
}
