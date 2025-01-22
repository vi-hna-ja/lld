package problems.amazonlocker.services;

import problems.amazonlocker.models.NotificationRequest;

public class SMSNotifierService implements NotificationObserver {
    @Override
    public void update(NotificationRequest request) {
        System.out.println("Sending SMS Notification to " + request.getCustomerName() +
                " with Locker ID: " + request.getLockerId() + " and PIN: " + request.getPin());
    }
}