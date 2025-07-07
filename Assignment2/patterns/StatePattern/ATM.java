public class ATM {
    private ATMState state;
    public void setState(ATMState s) { state = s; }
    public void handle() { state.handle(); }
}
