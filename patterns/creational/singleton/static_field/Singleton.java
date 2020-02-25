package patterns.creational.singleton.static_field;

// + Simple
// + Thread safety
// - No lazy initialization

public class Singleton {
    public static final Singleton INSTANCE = new Singleton();
}