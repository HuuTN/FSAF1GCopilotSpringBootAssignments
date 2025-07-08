package singleton;
// Singleton.java
// The Singleton pattern ensures a class has only one instance and provides a global point of access to it.
// This is useful when exactly one object is needed to coordinate actions across the system.

public class Singleton {
    // The single instance of the class (private static)
    private static Singleton instance;

    // Private constructor prevents instantiation from other classes
    private Singleton() {}

    // Global access point to get the instance
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Singleton instance called!");
    }
}

// Usage example:
// Singleton s = Singleton.getInstance();
// s.showMessage(); 