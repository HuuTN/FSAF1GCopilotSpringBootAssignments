package strategy;
// PaymentContext.java
// Context class for Strategy pattern
// Maintains a reference to a PaymentStrategy and delegates payment

public class PaymentContext {
    private PaymentStrategy strategy;
    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }
    public void pay(int amount) {
        strategy.pay(amount);
    }
}

// Usage example:
// PaymentContext context = new PaymentContext();
// context.setStrategy(new CreditCardPayment());
// context.pay(100); 