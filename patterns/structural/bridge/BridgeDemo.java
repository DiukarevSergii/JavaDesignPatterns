package patterns.structural.bridge;

interface Color {
    String fill();
}

class Blue implements Color {
    @Override
    public String fill() {
        return "Color is Blue";
    }
}

class Red implements Color {
    @Override
    public String fill() {
        return "Color is Red";
    }
}

abstract class Shape {
    protected Color color;

    public Shape(Color color) {
        this.color = color;
    }

    abstract public String draw();
}

class Square extends Shape {

    public Square(Color color) {
        super(color);
    }

    @Override
    public String draw() {
        return "Square drawn. " + color.fill();
    }
}

class Triangle extends Shape {

    public Triangle(Color color) {
        super(color);
    }

    @Override
    public String draw() {
        return "Triangle drawn. " + color.fill();
    }
}

// This is a good choice in the following cases:

// - When we want a parent abstract class to define the set of basic rules,
// and the concrete classes to add additional rules

// - When we have an abstract class that has a reference to the objects,
// and it has abstract methods that will be defined in each of the concrete classes

public class BridgeDemo {
    public static void main(String[] args) {
        Square square = new Square(new Red());
        Triangle triangle = new Triangle(new Blue());

        System.out.println(square.draw());
        System.out.println(triangle.draw());
    }
}
