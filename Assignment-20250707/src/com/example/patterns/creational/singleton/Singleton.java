// Implement a thread-safe Singleton class in Java with lazy initialization and a static method to get the instance.
package patterns.creational.singleton;

public class Singleton {
    private static Singleton instance;

    // Private constructor to prevent instantiation
    private Singleton() {
        // Initialization code here
    }

    // Public method to provide access to the instance
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}
