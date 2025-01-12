package problems.parkinglot;

public class ParkingSpot {
    private int spotNumber;
    private VehicleType type;
    private boolean isOccupied;

    ParkingSpot(int spotNumber, VehicleType type) {
        this.type = type;
        this.isOccupied = false;
        this.spotNumber = spotNumber;
    }

    public void lockParkingSpot() {
        this.isOccupied = true;
    }

    public void releaseParkingSpot() {
        this.isOccupied = false;
    }

    public int getSpotNumber() {
        return this.spotNumber;
    }

    public VehicleType getType() {
        return this.type;
    }

    public boolean isOccupied() {
        return this.isOccupied;
    }
}
