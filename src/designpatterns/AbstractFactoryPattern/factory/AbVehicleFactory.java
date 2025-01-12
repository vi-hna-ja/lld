package designpatterns.AbstractFactoryPattern.factory;

import designpatterns.AbstractFactoryPattern.vehicles.Vehicle;

public interface AbVehicleFactory {
    Vehicle getVehicle(String segment, int model);
}
