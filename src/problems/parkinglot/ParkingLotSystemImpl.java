package problems.parkinglot;

import java.util.List;

public class ParkingLotSystemImpl implements ParkingLotSystem {

    ParkingLot parkingLot;

    public ParkingLotSystemImpl(int levels) {
        parkingLot = ParkingLot.getParkingLotInstance(levels);
    }

    @Override
    public void addParkingSpot(int spotNumber, int levelNumber, VehicleType type) {
        parkingLot.addParkingSpot(spotNumber, levelNumber, type);
    }

    @Override
    public void parkVehicle(int spotNumber, int levelNumber, VehicleType type) {
        ParkingSpot parkingSpot = parkingLot.getParkingSpot(spotNumber, levelNumber);
        if (null == parkingSpot) {
            System.out.printf("Parking spot %s not found on level %s%n",
                    spotNumber, levelNumber);
        }

        synchronized (parkingSpot) {
            if (parkingSpot.isOccupied()) {
                System.out.printf("Parking spot %s is unavailable on level %s%n",
                        spotNumber, levelNumber);
                return;
            }
            if (type != parkingSpot.getType()) {
                System.out.printf("Parking spot %s on level %s does not support type %s%n",
                        spotNumber, levelNumber, type);
                return;
            }

            System.out.printf("Parking vehicle %s on spot %s on level %s\n",
                    type, spotNumber, levelNumber);
            parkingLot.getParkingLevel(levelNumber).lockParkingSpot(spotNumber, levelNumber);
        }
    }

    @Override
    public void exitVehicle(int spotNumber, int levelNumber) {
        ParkingSpot parkingSpot = parkingLot.getParkingSpot(spotNumber, levelNumber);
        if (null == parkingSpot) {
            System.out.printf("Parking spot %s not found on level %s%n",
                    spotNumber, levelNumber);
            return;
        }

        synchronized (parkingSpot) {
            if (!parkingSpot.isOccupied()) {
                System.out.printf("Parking spot %s is not occupied on level %s to release the vehicle%n",
                        spotNumber, levelNumber);
                return;
            }

            System.out.printf("Releasing vehicle %s on spot %s on level %s\n",
                    parkingSpot.getType(), spotNumber, levelNumber);
            parkingLot.getParkingLevel(levelNumber).releaseParkingSpot(spotNumber, levelNumber);
        }
    }
}
