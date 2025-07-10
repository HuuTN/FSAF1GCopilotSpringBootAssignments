public class PaymentContext {
    private PaymentStrategy strategy;
    public void setStrategy(PaymentStrategy s) { strategy = s; }
    public void pay(int amt) { strategy.pay(amt); }
}
