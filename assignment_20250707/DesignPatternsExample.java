import java.util.*;
// DesignPatternsExample.java
// Example implementations of Singleton, Factory, Observer, and Strategy patterns
// for fresher Java developers

// --- Singleton Pattern ---
class Singleton {
    private static Singleton instance;
    private Singleton() {}
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

// --- Factory Pattern ---
interface Animal {
    void speak();
}
class Dog implements Animal {
    public void speak() { System.out.println("Woof!"); }
}
class Cat implements Animal {
    public void speak() { System.out.println("Meow!"); }
}
class AnimalFactory {
    public static Animal createAnimal(String type) {
        if ("dog".equalsIgnoreCase(type)) return new Dog();
        if ("cat".equalsIgnoreCase(type)) return new Cat();
        return null;
    }
}

// --- Observer Pattern ---
interface Observer {
    void update(String message);
}
class ConcreteObserver implements Observer {
    private String name;
    public ConcreteObserver(String name) { this.name = name; }
    public void update(String message) {
        System.out.println(name + " received: " + message);
    }
}
class Subject {
    private List<Observer> observers = new ArrayList<>();
    public void addObserver(Observer o) { observers.add(o); }
    public void removeObserver(Observer o) { observers.remove(o); }
    public void notifyObservers(String message) {
        for (Observer o : observers) o.update(message);
    }
}

// --- Strategy Pattern ---
interface PaymentStrategy {
    void pay(int amount);
}
class CreditCardPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " by Credit Card");
    }
}
class PaypalPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " by Paypal");
    }
}
class PaymentContext {
    private PaymentStrategy strategy;
    public void setStrategy(PaymentStrategy strategy) { this.strategy = strategy; }
    public void pay(int amount) { strategy.pay(amount); }
}

// --- Main class to demonstrate all patterns ---
public class DesignPatternsExample {
    public static void main(String[] args) {
        // Singleton
        Singleton s = Singleton.getInstance();
        s.showMessage();

        // Factory
        Animal dog = AnimalFactory.createAnimal("dog");
        Animal cat = AnimalFactory.createAnimal("cat");
        dog.speak();
        cat.speak();

        // Observer
        Subject subject = new Subject();
        Observer obs1 = new ConcreteObserver("Observer1");
        Observer obs2 = new ConcreteObserver("Observer2");
        subject.addObserver(obs1);
        subject.addObserver(obs2);
        subject.notifyObservers("Hello Observers!");

        // Strategy
        PaymentContext context = new PaymentContext();
        context.setStrategy(new CreditCardPayment());
        context.pay(100);
        context.setStrategy(new PaypalPayment());
        context.pay(200);
    }
} 