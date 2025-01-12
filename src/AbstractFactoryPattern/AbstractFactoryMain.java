package AbstractFactoryPattern;

import AbstractFactoryPattern.factory.SegmentFactory;
import AbstractFactoryPattern.vehicles.Vehicle;

public class AbstractFactoryMain {
    public void run() {
        SegmentFactory segmentFactory = new SegmentFactory();
        Vehicle vehicle = segmentFactory.getVehicle("LUXURY", 1);
        System.out.printf("Average of the vehicle is: %s \n", vehicle.getAverage());

        Vehicle vehicle2 = segmentFactory.getVehicle("ORDINARY", 2);
        System.out.printf("Average of the vehicle is: %s \n", vehicle2.getAverage());
    }
}
