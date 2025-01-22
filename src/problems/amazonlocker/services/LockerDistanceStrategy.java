package problems.amazonlocker.services;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import problems.amazonlocker.models.Location;

public interface LockerDistanceStrategy {
    Optional<Location> execute(ConcurrentHashMap<String, Location> lockerLocations, double customerLat,
            double customerLong);
}
