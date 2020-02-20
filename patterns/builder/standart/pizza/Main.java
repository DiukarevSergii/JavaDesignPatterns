package patterns.builder.standart.pizza;

import static patterns.builder.standart.pizza.NyPizza.Size.SMALL;
import static patterns.builder.standart.pizza.Pizza.Topping.*;

public class Main {
    public static void main(String[] args) {
        NyPizza pizza = new NyPizza.Builder(SMALL) .addTopping(SAUSAGE).addTopping(ONION).build();
        Calzone calzone = new Calzone.Builder() .addTopping(HAM).sauceInside().build();
    }
}
