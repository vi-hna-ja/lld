package problems.parkinglot;

public interface ParkingLotSystem {

    void addParkingSpot(int spotNumber, int levelNumber, VehicleType type);

    void parkVehicle(int spotNumber, int levelNumber, VehicleType type);

    void exitVehicle(int spotNumber, int levelNumber);

}
