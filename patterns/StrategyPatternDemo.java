// Strategy pattern example
interface PaymentStrategy {
    void pay(int amount);
}
class CreditCardPayment implements PaymentStrategy {
    public void pay(int amount) { System.out.println("Paid " + amount + " by credit card"); }
}
class PaypalPayment implements PaymentStrategy {
    public void pay(int amount) { System.out.println("Paid " + amount + " by PayPal"); }
}
class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
    public void checkout(int amount) {
        paymentStrategy.pay(amount);
    }
}
