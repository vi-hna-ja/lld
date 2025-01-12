package problems.pubsub;

import problems.pubsub.models.Topic;
import problems.pubsub.publishers.BeautyPublisher;
import problems.pubsub.publishers.CookingPublisher;
import problems.pubsub.publishers.LifeStylePublisher;
import problems.pubsub.publishers.Publisher;
import problems.pubsub.subscribers.Subscriber;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PublisherManager {

    private static PublisherManager publisherManager;
    private ConcurrentHashMap<String, Subscriber> users;
    private Map<Topic, Publisher> publisherMap;
    private PublisherManager() {
        this.users = new ConcurrentHashMap<>();
        this.publisherMap = new HashMap<>();
        publisherMap.put(Topic.BEAUTY, getPublisher(Topic.BEAUTY));
        publisherMap.put(Topic.COOKING, getPublisher(Topic.COOKING));
        publisherMap.put(Topic.LIFESTYLE, getPublisher(Topic.LIFESTYLE));
    }

    public static PublisherManager getPublisherManager() {
        if (publisherManager == null) {
            synchronized (PublisherManager.class) {
                if (publisherManager == null) {
                    publisherManager = new PublisherManager();
                }
            }
        }
        return publisherManager;
    }

    public void sendMessage(Topic topic, String message) {
        // get the publisher from the topic using visitor pattern
        // use notify method of the publisher
        Publisher publisher = publisherMap.get(topic);
        publisher.notify(message);
    }

    public void subscribe(Topic topic, String email) {
        // get the publisher from the topic using visitor pattern
        // check if there is a user with already there
        // use sub method
        Subscriber subscriber = users.getOrDefault(email, new Subscriber(email));
        Publisher publisher = publisherMap.get(topic);
        if (publisher.checkSubscription(subscriber)) {
            System.out.printf("Subscriber %s has already subscribed to topic %s\n",
                    subscriber.getEmail(), topic);
            return;
        }

        publisher.subscribe(subscriber);
    }

    public void unsubscribe(Topic topic, String email) {
        // get the publisher from the topic using visitor pattern
        // check if there is a user with already there
        // use unsub method
        Subscriber subscriber = users.getOrDefault(email, new Subscriber(email));
        Publisher publisher = publisherMap.get(topic);
        if (!publisher.checkSubscription(subscriber)) {
            System.out.printf("Subscriber %s has already unsubscribed to topic %s\n",
                    subscriber.getEmail(), topic);
            return;
        }

        publisher.unsubscribe(subscriber);
    }

    private Publisher getPublisher(Topic topic) {
        return topic.accept(new Topic.TopicVisitor<Publisher>() {
            @Override
            public Publisher visitBeauty() {
                return new BeautyPublisher();
            }

            @Override
            public Publisher visitCooking() {
                return new CookingPublisher();
            }

            @Override
            public Publisher visitLifeStyle() {
                return new LifeStylePublisher();
            }
        });
    }
}
