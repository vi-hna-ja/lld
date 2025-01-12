package designpatterns.AbstractFactoryPattern.factory;

import designpatterns.AbstractFactoryPattern.vehicles.Vehicle;

public class SegmentFactory {

    // this is a factory that wraps another factory
    public Vehicle getVehicle(String segment, int model) {
        System.out.printf("Creating new vehicle in %s segment of model %s\n", segment, model);
        if (segment.equals("LUXURY")) {
            return new LuxurySegmentVehicles().getVehicle(segment, model);
        }
        else return new OrdinarySegmentVehicles().getVehicle(segment, model);
    }
}
