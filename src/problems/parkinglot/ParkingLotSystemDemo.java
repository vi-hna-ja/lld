package problems.parkinglot;

/*
Requirements:
    The parking lot should have multiple levels, each level with a certain number of parking spots.
    The parking lot should support different types of vehicles, such as cars, motorcycles, and trucks.
    Each parking spot should be able to accommodate a specific type of vehicle.
    The system should assign a parking spot to a vehicle upon entry and release it when the vehicle exits.
    The system should track the availability of parking spots and provide real-time information to customers.
    The system should handle multiple entry and exit points and support concurrent access.
 */
public class ParkingLotSystemDemo {
    public void demo() {
        ParkingLotSystemImpl parkingLotSystem = new ParkingLotSystemImpl(5);
        parkingLotSystem.addParkingSpot(0, 0, VehicleType.TWO_WHEELER);
        parkingLotSystem.addParkingSpot(1, 0, VehicleType.FOUR_WHEELER);
        parkingLotSystem.addParkingSpot(0, 1, VehicleType.TWO_WHEELER);
        parkingLotSystem.addParkingSpot(1, 1, VehicleType.EIGHT_WHEELER);

        parkingLotSystem.parkVehicle(0, 0, VehicleType.EIGHT_WHEELER);
        parkingLotSystem.parkVehicle(0, 0, VehicleType.TWO_WHEELER);
        parkingLotSystem.exitVehicle(0, 0);
    }
}
