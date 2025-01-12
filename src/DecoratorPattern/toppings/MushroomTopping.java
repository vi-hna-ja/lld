package DecoratorPattern.toppings;

import DecoratorPattern.pizza.BasePizza;

public class MushroomTopping extends Toppings {

    BasePizza basePizza;
    public MushroomTopping(BasePizza basePizza) {
        this.basePizza = basePizza;
    }

    @Override
    public int cost() {
        return basePizza.cost() + 10;
    }
}
