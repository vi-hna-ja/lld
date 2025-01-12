package AbstractFactoryPattern.factory;

import AbstractFactoryPattern.vehicles.Vehicle;

public interface AbVehicleFactory {
    Vehicle getVehicle(String segment, int model);
}
