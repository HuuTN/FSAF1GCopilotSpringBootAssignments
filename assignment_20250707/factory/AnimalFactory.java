package factory;
// AnimalFactory.java
// Factory class to create Animal objects
// Factory pattern provides a way to create objects without specifying the exact class of object that will be created.

public class AnimalFactory {
    public static Animal createAnimal(String type) {
        if ("dog".equalsIgnoreCase(type)) return new Dog();
        if ("cat".equalsIgnoreCase(type)) return new Cat();
        return null;
    }
}

// Usage example:
// Animal dog = AnimalFactory.createAnimal("dog");
// dog.speak(); 