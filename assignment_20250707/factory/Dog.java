package factory;
// Dog.java
// Dog class implements Animal interface
// Represents a concrete product in Factory pattern

public class Dog implements Animal {
    @Override
    public void speak() {
        System.out.println("Woof!");
    }
} 