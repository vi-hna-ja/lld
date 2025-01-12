package designpatterns.DecoratorPattern.toppings;

import designpatterns.DecoratorPattern.pizza.BasePizza;

public class JalepenoTopping extends Toppings {

    BasePizza basePizza;
    public JalepenoTopping(BasePizza basePizza) {
        this.basePizza = basePizza;
    }

    @Override
    public int cost() {
        return basePizza.cost() + 20;
    }
}
