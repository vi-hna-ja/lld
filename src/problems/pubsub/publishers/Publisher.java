package problems.pubsub.publishers;

import problems.pubsub.subscribers.Subscriber;

public interface Publisher {
    void subscribe(Subscriber subscriber);
    void unsubscribe(Subscriber subscriber);
    void notify(String news);
    boolean checkSubscription(Subscriber subscriber);
}
