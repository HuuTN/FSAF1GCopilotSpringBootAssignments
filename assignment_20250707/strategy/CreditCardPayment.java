package strategy;
// CreditCardPayment.java
// Concrete strategy for credit card payment

public class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " by Credit Card");
    }
} 