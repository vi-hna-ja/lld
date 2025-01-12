package problems.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private static volatile ParkingLot parkingLot;

    private final List<ParkingLevel> parkingLevels;

    private ParkingLot(int levels) {
        parkingLevels = new ArrayList<>();
        for (int i = 0; i < levels; i++) {
            parkingLevels.add(new ParkingLevel(i));
        }
    }

    public static ParkingLot getParkingLotInstance(int levels) {
        if (parkingLot == null) {
            synchronized (ParkingLot.class) {
                if (parkingLot == null) {
                    parkingLot = new ParkingLot(levels);
                }
            }
        }
        return parkingLot;
    }

    public void addParkingSpot(int spotNumber, int levelNumber, VehicleType type) {
        List<ParkingLevel> parkingLevelList = parkingLot.getAllParkingLevels();
        if (parkingLevelList.size() < levelNumber) {
            System.out.printf("invalid level number %s%n", levelNumber);
            return;
        }
        else if (parkingLevelList.size() == levelNumber) {
            parkingLevelList.add(new ParkingLevel(levelNumber));
        }

        parkingLevelList.get(levelNumber).addParkingSpot(spotNumber, type);
    }

    public List<ParkingLevel> getAllParkingLevels() {
        return this.parkingLevels;
    }

    public ParkingLevel getParkingLevel(int levelNumber) {
        return parkingLevels.get(levelNumber);
    }

    public ParkingSpot getParkingSpot(int spotNumber, int levelNumber) {
        return parkingLevels.get(levelNumber).getParkingSpot(spotNumber);
    }
}
