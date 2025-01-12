package problems.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLevel {
    private int levelNumber;
    private List<ParkingSpot> parkingSpotList;

    ParkingLevel(int levelNumber) {
        this.levelNumber = levelNumber;
        this.parkingSpotList = new ArrayList<>();
    }

    public void addParkingSpot(int spotNumber, VehicleType type) {
        if (parkingSpotList.size() < spotNumber) {
            System.out.printf("Invalid spot number %s on level %s%n", spotNumber, this.levelNumber);
        }

        parkingSpotList.add(new ParkingSpot(spotNumber, type));
    }

    public void lockParkingSpot(int spotNumber, int levelNumber) {
        if (levelNumber != this.levelNumber) {
            System.out.println("Incorrect level number" + levelNumber);
            return;
        }
        parkingSpotList.get(spotNumber).lockParkingSpot();
    }

    public void releaseParkingSpot(int spotNumber, int levelNumber) {
        if (levelNumber != this.levelNumber) {
            System.out.println("Incorrect level number" + levelNumber);
            return;
        }
        parkingSpotList.get(spotNumber).releaseParkingSpot();
    }

    public ParkingSpot getParkingSpot(int spotNumber) {
        if (parkingSpotList.size() < spotNumber)
            return null;
        return parkingSpotList.get(spotNumber);
    }
}
