// Main.java
// Demonstrates usage of Singleton, Factory, Observer, and Strategy patterns
// Each pattern is explained in detail for fresher Java developers

import singleton.Singleton;
import factory.Animal;
import factory.AnimalFactory;
import observer.Subject;
import observer.Observer;
import observer.ConcreteObserver;
import strategy.PaymentStrategy;
import strategy.CreditCardPayment;
import strategy.PaypalPayment;
import strategy.PaymentContext;

public class Main {
    public static void main(String[] args) {
        // --- Singleton Pattern ---
        // Ensures only one instance of a class exists and provides a global access point.
        Singleton s = Singleton.getInstance();
        s.showMessage();

        // --- Factory Pattern ---
        // Creates objects without exposing the instantiation logic to the client.
        Animal dog = AnimalFactory.createAnimal("dog");
        Animal cat = AnimalFactory.createAnimal("cat");
        dog.speak();
        cat.speak();

        // --- Observer Pattern ---
        // Defines a one-to-many dependency so that when one object changes state, all its dependents are notified.
        Subject subject = new Subject();
        Observer obs1 = new ConcreteObserver("Observer1");
        Observer obs2 = new ConcreteObserver("Observer2");
        subject.addObserver(obs1);
        subject.addObserver(obs2);
        subject.notifyObservers("Hello Observers!");

        // --- Strategy Pattern ---
        // Enables selecting an algorithm's behavior at runtime.
        PaymentContext context = new PaymentContext();
        PaymentStrategy creditCard = new CreditCardPayment();
        PaymentStrategy paypal = new PaypalPayment();
        context.setStrategy(creditCard);
        context.pay(100);
        context.setStrategy(paypal);
        context.pay(200);
    }
} 