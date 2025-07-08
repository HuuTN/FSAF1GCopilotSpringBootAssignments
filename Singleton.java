//Task 1 : Writing Effective Comments

//Implement Singleton Design Pattern with Lazy Initialization and threead safety using synchronized block
public class Singleton {
    private static Singleton instance;

    // Private constructor to prevent instantiation
    private Singleton() {
    }

    // Public method to provide access to the instance
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}