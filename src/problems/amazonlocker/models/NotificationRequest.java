package problems.amazonlocker.models;

public class NotificationRequest {
    private final String customerName;
    private final String pin;
    private final String packageId;
    private final String lockerId;

    public NotificationRequest(String customerName, String pin, String packageId, String lockerId) {
        this.customerName = customerName;
        this.pin = pin;
        this.packageId = packageId;
        this.lockerId = lockerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPin() {
        return pin;
    }

    public String getPackageId() {
        return packageId;
    }

    public String getLockerId() {
        return lockerId;
    }
}
