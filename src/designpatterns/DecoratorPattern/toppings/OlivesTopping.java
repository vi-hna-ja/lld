package designpatterns.DecoratorPattern.toppings;

import designpatterns.DecoratorPattern.pizza.BasePizza;

public class OlivesTopping extends Toppings {

    BasePizza basePizza;

    public OlivesTopping(BasePizza basePizza) {
        this.basePizza = basePizza;
    }

    @Override
    public int cost() {
        return basePizza.cost() + 30;
    }
}
