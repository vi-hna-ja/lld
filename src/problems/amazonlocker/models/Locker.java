package problems.amazonlocker.models;

import java.time.LocalDate;
import problems.amazonlocker.services.Utils;

public class Locker {
    private final String lockerId;
    private boolean available;
    private String pin;
    private final PackageSize size;
    private LocalDate lastUsedDate;
    private double latitude;
    private double longitude;

    public Locker(PackageSize size) {
        this.lockerId = Utils.generateId("LOC");
        this.available = true;
        this.size = size;
        this.pin = null;
        this.lastUsedDate = null;
    }

    public String getLockerId() {
        return lockerId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public PackageSize getSize() {
        return size;
    }

    public LocalDate getLastUsedDate() {
        return lastUsedDate;
    }

    public void setLastUsedDate(LocalDate lastUsedDate) {
        this.lastUsedDate = lastUsedDate;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
