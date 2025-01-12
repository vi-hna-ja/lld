package BuilderPattern;

import BuilderPattern.builders.Vehicle;
import BuilderPattern.builders.VehicleBuilder;

public class Director {

    VehicleBuilder vehicleBuilder;

    public Director(VehicleBuilder vehicleBuilder) {
        this.vehicleBuilder = vehicleBuilder;
    }

    public Vehicle buildCar() {
        vehicleBuilder.setSeats(4);
        vehicleBuilder.setWheels(4);
        vehicleBuilder.setSeatColour("BLACK");
        vehicleBuilder.setEnginePower(400);
        return vehicleBuilder.build();
    }

    public Vehicle buildBike() {
        vehicleBuilder.setSeats(2);
        vehicleBuilder.setWheels(2);
        vehicleBuilder.setSeatColour("GREY");
        vehicleBuilder.setEnginePower(110);
        return vehicleBuilder.build();
    }
}
