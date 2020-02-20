package patterns.singleton;

// Singleton with static factory
public class ElvisClass {
    private static final ElvisClass INSTANCE = new ElvisClass();

    private ElvisClass() {
        //...
    }

    public static ElvisClass getInstance() {
        return INSTANCE;
    }

    public void leaveTheBuilding() {
//        ...
    }

    //A final advantage of using a static factory is that a method reference can be used as a supplier, for
    //example Elvis::instance is a Supplier<Elvis>. Unless one of these advantages is relevant, the public field approach is preferable.
    //To make a singleton class that uses either of these
    // approaches serializable (Chapter 12), it is not sufficient merely to add implements Serializable to its declaration.
    // To maintain the singleton guarantee, declare all instance fields transient and provide a readResolve method (Item 89).
    // Otherwise, each time a serialized instance is deserialized, a new instance will be created, leading,
    // in the case of our example, to spurious Elvis sightings. To prevent this from happening,
    // add this readResolvemethod to the Elvis class:

    // readResolve method to preserve singleton property
    private Object readResolve() {
        // Return the one true Elvis and let the garbage collector // take care of the Elvis impersonator.
        return INSTANCE;
    }
}
