package problems.amazonlocker.models;

import problems.amazonlocker.services.Utils;

public class Parcel {

    private PackageSize size;
    private String customerName;
    private String pin;
    private String lockerId;

    public Parcel(PackageSize size, String customerName) {
        this.size = size;
        this.customerName = customerName;
        this.pin = Utils.generatePin();
    }

    public PackageSize getSize() {
        return size;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPin() {
        return pin;
    }

    public void setLockerId(String lockerId) {
        this.lockerId = lockerId;
    }

    public String getLockerId() {
        return lockerId;
    }
}
