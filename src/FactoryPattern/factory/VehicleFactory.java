package FactoryPattern.factory;

import FactoryPattern.vehicles.*;

public class VehicleFactory {
    public Vehicle getVehicle(String input) {
        switch (input) {
            case "LUXURY-SEGMENT-1": return new BMWVehicle();
            case "LUXURY-SEGMENT-2": return new AudiVehicle();
            case "ORDINARY-SEGMENT-1": return new SwiftVehicle();
            default: return new CretaVehicle();
        }
    }
}
