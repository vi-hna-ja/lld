package problems.amazonlocker.models;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Location {

    private final String name;
    private final double latitude;
    private final double longitude;
    private final ConcurrentHashMap<PackageSize, List<Locker>> lockers;

    public Location(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.lockers = new ConcurrentHashMap<>();
        for (PackageSize size : PackageSize.values()) {
            lockers.put(size, new ArrayList<Locker>());
        }
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public ConcurrentHashMap<PackageSize, List<Locker>> getLockers() {
        return lockers;
    }
}
