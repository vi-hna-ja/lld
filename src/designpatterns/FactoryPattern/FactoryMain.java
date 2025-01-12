package designpatterns.FactoryPattern;

import designpatterns.FactoryPattern.factory.VehicleFactory;
import designpatterns.FactoryPattern.vehicles.Vehicle;

public class FactoryMain {

    public void run() {
        VehicleFactory vehicleFactory = new VehicleFactory();
        Vehicle vehicle = vehicleFactory.getVehicle("LUXURY-SEGMENT-3");
        System.out.println(vehicle.getAverage());
    }
}
