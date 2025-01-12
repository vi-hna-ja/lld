package DecoratorPattern;

import DecoratorPattern.pizza.BasePizza;
import DecoratorPattern.pizza.MargheritaPizza;
import DecoratorPattern.pizza.VeggieDelightPizza;
import DecoratorPattern.toppings.JalepenoTopping;
import DecoratorPattern.toppings.MushroomTopping;
import DecoratorPattern.toppings.OlivesTopping;

public class DecoratorMain {

    public void run() {
        BasePizza basePizza = new OlivesTopping(new MushroomTopping(new MargheritaPizza()));
        // 150 + 10 + 30
        System.out.printf("Margherita Pizza cost with toppings: %s \n", basePizza.cost());

        BasePizza basePizza1 = new JalepenoTopping(new OlivesTopping(new MushroomTopping(new VeggieDelightPizza())));
        // 200 + 10 + 30 + 20
        System.out.printf("Veggie delight pizza cost with toppings: %s \n", basePizza1.cost());
    }
}
