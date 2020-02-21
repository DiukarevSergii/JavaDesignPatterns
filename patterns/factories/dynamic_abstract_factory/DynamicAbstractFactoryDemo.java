package patterns.factories.dynamic_abstract_factory;

import org.reflections.Reflections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

interface IHotDrink {
    void consume();
}

class Tea implements IHotDrink {
    @Override
    public void consume() {
        System.out.println("This tea is nice but I'd prefer it with milk.");
    }
}

class Coffee implements IHotDrink {
    @Override
    public void consume() {
        System.out.println("This coffee is delicious");
    }
}

interface AbstractHotDrinkFactory {
    IHotDrink prepare(int amount);
}

class TeaFactory implements AbstractHotDrinkFactory {
    @Override
    public IHotDrink prepare(int amount) {
        System.out.println(
                "Put in tea bag, boil water, pour "
                        + amount + "ml, add lemon, enjoy!"
        );
        return new Tea();
    }
}

class CoffeeFactory implements AbstractHotDrinkFactory {

    @Override
    public IHotDrink prepare(int amount) {
        System.out.println(
                "Grind some beans, boil water, pour "
                        + amount + " ml, add cream and sugar, enjoy!"
        );
        return new Coffee();
    }
}

class HotDrinkMachine {
    public enum AvailableDrink {
        COFFEE, TEA
    }

    private Dictionary<AvailableDrink, AbstractHotDrinkFactory> factories =
            new Hashtable<>();

    private List<Map.Entry<String, AbstractHotDrinkFactory>> namedFactories =
            new ArrayList<>();

    public HotDrinkMachine() throws Exception {
        // option 1: use an enum
        for (AvailableDrink drink : AvailableDrink.values()) {
            String s = drink.toString();
            String factoryName = "" + Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase();
            Class<?> factory = Class.forName("patterns.factories.dynamic_abstract_factory." + factoryName + "Factory");
            factories.put(drink, (AbstractHotDrinkFactory) factory.getDeclaredConstructor().newInstance());
        }

        // option 2: find all implementors of IHotDrinkFactory
        Set<Class<? extends AbstractHotDrinkFactory>> types =
                new Reflections("patterns.factories.dynamic_abstract_factory") // ""
                        .getSubTypesOf(AbstractHotDrinkFactory.class);
        for (Class<? extends AbstractHotDrinkFactory> type : types) {
            namedFactories.add(new AbstractMap.SimpleEntry<>(
                    type.getSimpleName().replace("Factory", ""),
                    type.getDeclaredConstructor().newInstance()
            ));
        }
    }

    public IHotDrink makeDrink() throws IOException {
        System.out.println("Available drinks");
        for (int index = 0; index < namedFactories.size(); ++index) {
            Map.Entry<String, AbstractHotDrinkFactory> item = namedFactories.get(index);
            System.out.println("" + index + ": " + item.getKey());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String s;
            int i, amount;
            if ((s = reader.readLine()) != null
                    && (i = Integer.parseInt(s)) >= 0
                    && i < namedFactories.size()) {
                System.out.println("Specify amount: ");
                s = reader.readLine();
                if (s != null
                        && (amount = Integer.parseInt(s)) > 0) {
                    return namedFactories.get(i).getValue().prepare(amount);
                }
            }
            System.out.println("Incorrect input, try again.");
        }
    }

    public IHotDrink makeDrink(AvailableDrink drink, int amount) {
        return factories.get(drink).prepare(amount);
    }
}

class DynamicAbstractFactoryDemo {
    public static void main(String[] args) throws Exception {
        HotDrinkMachine machine = new HotDrinkMachine();
//        IHotDrink tea = machine.makeDrink(HotDrinkMachine.AvailableDrink.TEA, 200);
//        tea.consume();

        // interactive
        IHotDrink drink = machine.makeDrink();
        drink.consume();
    }
}

