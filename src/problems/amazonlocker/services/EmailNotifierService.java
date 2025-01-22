package problems.amazonlocker.services;

import problems.amazonlocker.models.NotificationRequest;

public class EmailNotifierService  implements NotificationObserver {
    @Override
    public void update(NotificationRequest request) {
        System.out.println("Sending Email Notification to " + request.getCustomerName() +
                " with Locker ID: " + request. getLockerId()+ " and PIN: " + request.getPin());
    }
}
