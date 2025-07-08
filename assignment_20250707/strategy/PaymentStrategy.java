package strategy;
// PaymentStrategy.java
// Strategy interface for payment methods
// Strategy pattern allows selecting an algorithm at runtime

public interface PaymentStrategy {
    void pay(int amount);
} 