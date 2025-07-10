// Legacy interface
class OldPaymentGateway {
    public void makePayment(String account, double amount) {
        System.out.println("OldPaymentGateway: Paid " + amount + " to " + account);
    }
}

// New interface
interface ModernPaymentInterface {
    void pay(String customerId, double amount);
}

// Adapter
class PaymentAdapter implements ModernPaymentInterface {
    private OldPaymentGateway oldGateway;

    public PaymentAdapter(OldPaymentGateway oldGateway) {
        this.oldGateway = oldGateway;
    }

    public void pay(String customerId, double amount) {
        oldGateway.makePayment(customerId, amount);
    }
}
