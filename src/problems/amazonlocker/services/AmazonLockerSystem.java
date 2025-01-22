package problems.amazonlocker.services;

import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;
import problems.amazonlocker.models.Customer;
import problems.amazonlocker.models.Location;
import problems.amazonlocker.models.Locker;
import problems.amazonlocker.models.LockerNotFoundException;
import problems.amazonlocker.models.PackageSize;
import problems.amazonlocker.models.Parcel;
import problems.amazonlocker.models.PinMismatchException;
import problems.chessgame.models.Player;

public class AmazonLockerSystem {

    private static AmazonLockerSystem lockerSystem;
    private final ConcurrentHashMap<String, Customer> customers;
    private final LockerManager lockerManager;
    private final PriorityBlockingQueue<Parcel> orders;

    private AmazonLockerSystem() {
        this.customers = new ConcurrentHashMap<>();
        this.lockerManager = new LockerManager();
        this.orders = new PriorityBlockingQueue<Parcel>();
    }

    public static AmazonLockerSystem getInstance() {
        if (lockerSystem == null) {
            synchronized (AmazonLockerSystem.class) {
                if (lockerSystem == null)
                    lockerSystem = new AmazonLockerSystem();
            }
        }
        return lockerSystem;
    }

    public Customer addCustomer(String name, String email, String phone) {
        Customer customer = new Customer(name, email, phone);
        customers.put(name, customer);
        return customer;
    }

    public synchronized String createLockerLocation(String name, double lati, double longi) {
        lockerManager.createLockerLocation(name, lati, longi);
        return name;
    }

    public void addLockerSlots(String lockerName, PackageSize size, int slots) {
        lockerManager.addLockersInALocation(lockerName, size, slots);
    }

    public synchronized Parcel placeOrder(Customer customer, PackageSize packageSize) {
        try {
            Parcel parcel = new Parcel(packageSize, customer.getName());
            orders.offer(parcel);

            Optional<Location> nearestLockerLocationOptional = lockerManager.getNearestLocation();
            if (nearestLockerLocationOptional.isEmpty()) {
                System.out.printf("Could not place order for size %s by customer %s. Initiating refund\n", packageSize,
                        customer.getName());
                throw new LockerNotFoundException();
            }

            Location nearestLocation = nearestLockerLocationOptional.get();
            Optional<Locker> optionalEmptyLocker = lockerManager.getEmptyLockerInALocation(nearestLocation,
                    packageSize);
            if (optionalEmptyLocker.isEmpty()) {
                System.out.printf("Couldn't find an empty locker of size %s in location %s\n",
                        packageSize, nearestLocation.getName());
                throw new LockerNotFoundException();
            }

            lockerManager.assignLockerToCustomer(nearestLocation, optionalEmptyLocker.get(), customer, packageSize);
            parcel.setLockerId(optionalEmptyLocker.get().getLockerId());
            return parcel;
        } catch (Exception e) {
            System.out.printf("Exception occurred. Message - %s\n", e.getMessage());
            return null;
        }
    }

    public synchronized void pickUpPackage(String name, Parcel parcel) throws PinMismatchException {
        Location location = lockerManager.getLocationByName(name);
        Locker locker = location.getLockers().get(parcel.getSize())
                        .stream()
                                .filter(data -> data.getLockerId().equals(parcel.getLockerId()))
                .findFirst()
                .get();
        lockerManager.pickupPackageFromLocker(location, locker, parcel);
        if (!parcel.getPin().equals(locker.getPin())) {
            throw new PinMismatchException();
        }
        lockerManager.pickupPackageFromLocker(location, locker, parcel);
    }

}
