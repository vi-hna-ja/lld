package BuilderPattern.builders;

public abstract class VehicleBuilder {
    int seats;
    int wheels;
    String seatColour;
    int power;

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setWheels(int wheels) {
        this.wheels = wheels;
    }

    public void setSeatColour(String colour) {
        this.seatColour = colour;
    }

    public void setEnginePower(int power) {
        this.power = power;
    }

    public Vehicle build() {
        return new Vehicle(this);
    }
}
