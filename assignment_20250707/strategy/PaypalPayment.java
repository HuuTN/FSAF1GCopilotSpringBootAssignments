package strategy;
// PaypalPayment.java
// Concrete strategy for Paypal payment

public class PaypalPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " by Paypal");
    }
} 