//Implement Singleton Design Pattern with lazy initialization and thread safety using synchronized blocks
public class Singleton {
    private static Singleton instance;

    // Private constructor prevents instantiation from other classes
    private Singleton() {}

    // Public method to provide access to the instance
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}