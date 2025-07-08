package factory;
// Cat.java
// Cat class implements Animal interface
// Represents a concrete product in Factory pattern

public class Cat implements Animal {
    @Override
    public void speak() {
        System.out.println("Meow!");
    }
} 