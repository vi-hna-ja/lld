package BuilderPattern.builders;

public class Vehicle {

    int seats;
    int wheels;
    String seatColour;
    int power;

    Vehicle(VehicleBuilder builder) {
        this.seats = builder.seats;
        this.wheels = builder.wheels;
        this.seatColour = builder.seatColour;
        this.power = builder.power;
    }

    public String toString() {
        return String.format("Vehicle details:[seats: %s, wheels: %s, colour: %s, power: %s]",
                seats, wheels, seatColour, power);
    }
}
