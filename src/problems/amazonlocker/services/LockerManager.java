package problems.amazonlocker.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import problems.amazonlocker.models.Customer;
import problems.amazonlocker.models.Location;
import problems.amazonlocker.models.Locker;
import problems.amazonlocker.models.LockerNotFoundException;
import problems.amazonlocker.models.PackageSize;
import problems.amazonlocker.models.Parcel;

public class LockerManager {
    private LockerDistanceStrategy lockerDistanceStrategy;
    private ConcurrentHashMap<String, Location> lockerLocations;
    private ReentrantLock lock;
    public LockerManager() {
        this.lockerLocations = new ConcurrentHashMap<>();
        lock = new ReentrantLock();
    }

    public Location createLockerLocation(String name, double lat, double longi) {
        Location location = new Location(name, lat, longi);
        lockerLocations.put(name, location);
        return location;
    }

    public synchronized void addLockersInALocation(String name, PackageSize size, int lockersCount) {
        Location location = getLocationByName(name);
        for (int i = 0; i < lockersCount; i++) {
            Locker locker = new Locker(size);
            locker.setLatitude(location.getLatitude());
            locker.setLongitude(location.getLongitude());
            location.getLockers().get(size).add(locker);
        }
    }

    public void setLockerDistanceStrategy(LockerDistanceStrategy newStrategy) {
        this.lockerDistanceStrategy = newStrategy;
    }

    public synchronized Optional<Location> getNearestLocation() throws LockerNotFoundException {
        System.out.println("Please enter your co-ordinates to find the nearest locker");
        Scanner scanner = new Scanner(System.in);
        double customerLatitude = scanner.nextDouble();
        double customerLongitude = scanner.nextDouble();

        return lockerDistanceStrategy.execute(lockerLocations, customerLatitude,
                customerLongitude);
    }

    public Optional<Locker> getEmptyLockerInALocation(Location location, PackageSize packageSize) {
        return location.getLockers().get(packageSize)
                .stream()
                .filter(Locker::isAvailable)
                .findAny();
    }

    public void assignLockerToCustomer(Location location, Locker locker, Customer customer, PackageSize packageSize) {
        lock.lock();
        try {
            System.out.printf("Assigning locker to customer %s. Generating pin..\n", customer.getName());
            location.getLockers().get(packageSize).remove(locker);
            locker.setAvailable(false);
            locker.setLastUsedDate(LocalDate.now());
            String lockerPin = Utils.generatePin();
            locker.setPin(lockerPin);
            location.getLockers().get(packageSize).add(locker);
            // notify customer on email and phone about the pin
        } finally {
            lock.unlock();
        }
    }

    public void pickupPackageFromLocker(Location location, Locker locker, Parcel parcel) {
        lock.lock();
        try {
            System.out.printf("Customer %s attempted to pick up parcel %s\n",
                    parcel.getCustomerName(), parcel.getSize());
            location.getLockers().get(parcel.getSize()).remove(locker);
            locker.setAvailable(true);
            locker.setLastUsedDate(LocalDate.now());
            location.getLockers().get(parcel.getSize()).add(locker);

            // notify customer on email and phone that parcel has been picked up
        } finally {
            lock.unlock();
        }
    }

    public Location getLocationByName(String name) {
        return lockerLocations.get(name);
    }
}
