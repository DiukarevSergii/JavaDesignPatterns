package patterns.creational.factories.abstract_factory;

interface Animal {
    String getAnimal();

    String makeSound();
}

class Duck implements Animal {

    @Override
    public String getAnimal() {
        return "Duck";
    }

    @Override
    public String makeSound() {
        return "Squeks";
    }

    @Override
    public String toString() {
        return String.format("%s, makeSound: %s", getAnimal(), makeSound());
    }
}

class Dog implements Animal {

    @Override
    public String getAnimal() {
        return "Dog";
    }

    @Override
    public String makeSound() {
        return "Bark";
    }

    @Override
    public String toString() {
        return String.format("%s, makeSound: %s", getAnimal(), makeSound());
    }
}

interface AbstractFactory<T> {
    T create(String animalType);
}

class AnimalFactory implements AbstractFactory<Animal> {

    @Override
    public Animal create(String animalType) {
        if ("Dog".equalsIgnoreCase(animalType)) {
            return new Dog();
        } else if ("Duck".equalsIgnoreCase(animalType)) {
            return new Duck();
        }

        return null;
    }

}

public class AbstractFactoryDemo {
    public static void main(String[] args) {
        AnimalFactory animalFactory = new AnimalFactory();
        Animal dog = animalFactory.create("Dog");
        Animal duck = animalFactory.create("Duck");
        System.out.println(dog);
        System.out.println(duck);
    }
}