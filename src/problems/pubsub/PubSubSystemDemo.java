package problems.pubsub;

import problems.pubsub.models.Topic;

public class PubSubSystemDemo {
    public void run() {
        PublisherManager publisherManager = PublisherManager.getPublisherManager();
        publisherManager.subscribe(Topic.BEAUTY, "user1.com");
        publisherManager.subscribe(Topic.COOKING, "user2.com");
        publisherManager.subscribe(Topic.COOKING, "user3.com");
        publisherManager.subscribe(Topic.LIFESTYLE, "user2.com");

        publisherManager.sendMessage(Topic.BEAUTY, "new skin cream");
        publisherManager.sendMessage(Topic.LIFESTYLE, "new dish to try");
        publisherManager.sendMessage(Topic.LIFESTYLE, "get longer life");

        publisherManager.unsubscribe(Topic.LIFESTYLE, "user3.com");
        publisherManager.unsubscribe(Topic.LIFESTYLE, "user2.com");
        publisherManager.sendMessage(Topic.LIFESTYLE, "get longer life");

    }
}
