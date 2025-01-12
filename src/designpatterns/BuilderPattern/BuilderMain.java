package designpatterns.BuilderPattern;

import designpatterns.BuilderPattern.builders.*;

public class BuilderMain {

    public void run() {
        Director carDirector = new Director(new CarBuilder());
        Vehicle vehicle = carDirector.buildCar();

        Director bikeDirector = new Director(new BikeBuilder());
        Vehicle vehicle1 = bikeDirector.buildBike();
        System.out.println(vehicle.toString() +  "\n" + vehicle1.toString());
    }
}
