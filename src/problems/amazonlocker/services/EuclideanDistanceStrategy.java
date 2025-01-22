package problems.amazonlocker.services;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import problems.amazonlocker.models.Location;

public class EuclideanDistanceStrategy implements LockerDistanceStrategy {

    @Override
    public Optional<Location> execute(ConcurrentHashMap<String, Location> lockerLocations, double customerLat,
            double customerLong) {
        String locationName = null;
        double minDistance = Double.MAX_VALUE;

        for (Map.Entry<String, Location> locationEntry : lockerLocations.entrySet()) {
            Location loc = locationEntry.getValue();
            double distance = calculateDistance(loc.getLatitude(), loc.getLongitude(), customerLat, customerLong);
            if (minDistance > distance) {
                minDistance = distance;
                locationName = locationEntry.getKey();
            }
        }

        return (null == locationName) ? Optional.empty() : Optional.of(lockerLocations.get(locationName));
    }

    private double calculateDistance(double locationLat, double locationLong, double customerLat, double customerLong) {
        return Math.abs(locationLat - customerLat) * 10.0 + Math.abs((locationLong - customerLong)) / 10.0;
    }
}
