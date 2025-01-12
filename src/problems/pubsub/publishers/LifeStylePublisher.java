package problems.pubsub.publishers;

import problems.pubsub.subscribers.Subscriber;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LifeStylePublisher implements Publisher {

    private List<Subscriber> subscribers = new ArrayList<>();
    private Set<String> uniqueNews = new HashSet<>();

    @Override
    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
        System.out.printf("User %s just subscribed to LIFESTYLE!. Subscribers count now %s\n",
                subscriber.getEmail(), subscribers.size());
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
        System.out.printf("User %s unsubscribed to LIFESTYLE :(((. Subscribers count now %s\n",
                subscriber.getEmail(), subscribers.size());
    }

    @Override
    public void notify(String news) {
        if (uniqueNews.contains(news)) {
            System.out.printf("The message [%s] has already been notified to our subscribers\n", news);
            return;
        }

        uniqueNews.add(news);
        for (Subscriber subscriber : subscribers) {
            subscriber.update(news);
        }
    }

    @Override
    public boolean checkSubscription(Subscriber subscriber) {
        return subscribers.contains(subscriber);
    }
}

