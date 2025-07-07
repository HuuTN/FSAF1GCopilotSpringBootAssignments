public class CreditCard implements PaymentStrategy {
    public void pay(int amt) { System.out.println("CreditCard: " + amt); }
}
