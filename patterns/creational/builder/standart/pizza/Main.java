package patterns.creational.builder.standart.pizza;

import static patterns.creational.builder.standart.pizza.NyPizza.Size.SMALL;
import static patterns.creational.builder.standart.pizza.Pizza.Topping.*;

public class Main {
    public static void main(String[] args) {
        NyPizza pizza = new NyPizza.Builder(SMALL) .addTopping(SAUSAGE).addTopping(ONION).build();
        Calzone calzone = new Calzone.Builder() .addTopping(HAM).sauceInside().build();
    }
}
