package patterns.creational.singleton.demand_holder_idiom;

// + Lazy initialization
// + High performance
// - Cannot be used for non-static class fields

public class Singleton {

    public static class SingletonHolder {
        public static final Singleton HOLDER_INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }
}