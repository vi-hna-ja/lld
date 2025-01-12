package AbstractFactoryPattern.factory;

import AbstractFactoryPattern.vehicles.CretaVehicle;
import AbstractFactoryPattern.vehicles.SwiftVehicle;
import AbstractFactoryPattern.vehicles.Vehicle;

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
