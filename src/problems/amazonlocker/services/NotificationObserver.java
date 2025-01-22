package problems.amazonlocker.services;

import problems.amazonlocker.models.NotificationRequest;

public interface NotificationObserver {
    void update(NotificationRequest request);
}
