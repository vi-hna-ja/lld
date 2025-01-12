package designpatterns.AbstractFactoryPattern.factory;

import designpatterns.AbstractFactoryPattern.vehicles.CretaVehicle;
import designpatterns.AbstractFactoryPattern.vehicles.SwiftVehicle;
import designpatterns.AbstractFactoryPattern.vehicles.Vehicle;

public class OrdinarySegmentVehicles implements AbVehicleFactory {
    @Override
    public Vehicle getVehicle(String segment, int model) {
        if (segment.equals("LUXURY") || model > 2) {
            System.out.printf("%s - %s kind is not supported in this segment", segment, model);
            return null;
        }
        if (model == 1) {
            return new SwiftVehicle();
        } else {
            return new CretaVehicle();
        }
    }
}
