public class StatePatternDemo {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.setState(new Ready());
        atm.handle();
        atm.setState(new HasCard());
        atm.handle();
        atm.setState(new NoCash());
        atm.handle();
    }
}
