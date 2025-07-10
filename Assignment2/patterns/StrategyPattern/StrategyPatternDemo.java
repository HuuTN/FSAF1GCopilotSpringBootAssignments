public class StrategyPatternDemo {
    public static void main(String[] args) {
        PaymentContext ctx = new PaymentContext();
        ctx.setStrategy(new CreditCard());
        ctx.pay(100);
        ctx.setStrategy(new PayPal());
        ctx.pay(200);
        ctx.setStrategy(new Momo());
        ctx.pay(300);
    }
}
