package AbstractFactoryPattern.factory;

import AbstractFactoryPattern.vehicles.AudiVehicle;
import AbstractFactoryPattern.vehicles.BMWVehicle;
import AbstractFactoryPattern.vehicles.Vehicle;

public class LuxurySegmentVehicles implements AbVehicleFactory {

    @Override
    public Vehicle getVehicle(String segment, int model) {
        if (segment.equals("ORDINARY") || model > 2) {
            System.out.printf("%s - %s kind is not supported in this segment", segment, model);
            return null;
        }
        if (model == 1) {
            return new BMWVehicle();
        } else {
            return new AudiVehicle();
        }
    }
}
