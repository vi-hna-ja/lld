package problems.amazonlocker;

import problems.amazonlocker.models.Customer;
import problems.amazonlocker.models.PackageSize;
import problems.amazonlocker.models.Parcel;
import problems.amazonlocker.services.AmazonLockerSystem;

/*
Design an Amazon Locker Management System to facilitate the efficient management of package deliveries and pickups at various locations. The system should enable customers to order packages and assign lockers for secure storage until pickup.

Requirements:
Customer Operations:
    Customers should be able to order packages of different sizes (small, medium, large).
    Upon ordering a package, the system should assign a locker of an appropriate size to the customer.
    Customers should receive a unique PIN to access their assigned locker.
    Customers should be able to unassign their locker by providing the correct PIN.
Locker Management:
    The system should maintain a list of lockers at each location, categorizing them by size (small, medium, large).
    Lockers should be assigned to customers based on availability and package size.
    Once a package is picked up, the locker should be marked as available for reuse.
Location Management:
    The system should support multiple locations, each with its set of lockers.
    Customers should be assigned lockers from the nearest location to their current coordinates.
    Each location should have specified opening and closing times.
4.Security:
    Customers should only be able to access lockers assigned to them using their unique PIN.
    The system should ensure that packages are securely stored and accessible only to the assigned customer.
5.Scalability:
    The system should be scalable to accommodate a large number of customers and packages.
    It should efficiently handle concurrent requests for package ordering, locker assignment, and unassignment.
6. Notification:
    Customers should receive notifications upon successful locker assignment, providing them with the locker ID and PIN.
    Notifications should also be sent if there are any issues with locker assignment or unassignment.
 */

public class AmazonLockerSystemDemo {

    public void run() {
        AmazonLockerSystem system = AmazonLockerSystem.getInstance();
        Customer customer = system.addCustomer("J", "email@pp.com", "1234567");
        String locationOne = system.createLockerLocation("aero mall", 123.45, -09.809);
        String locationTwo = system.createLockerLocation("airport", 42.89, 78.56);
        system.addLockerSlots(locationOne, PackageSize.S, 3);
        system.addLockerSlots(locationOne, PackageSize.L, 5);
        system.addLockerSlots(locationTwo, PackageSize.M, 4);

        Parcel parcel = system.placeOrder(customer, PackageSize.XL);
        if (parcel == null) {
            System.out.printf("Could not place order. Please try again\n");
            return;
        }

        try {
            system.pickUpPackage(customer.getName(), parcel);
        }
        catch (Exception e) {

        }
    }

}
