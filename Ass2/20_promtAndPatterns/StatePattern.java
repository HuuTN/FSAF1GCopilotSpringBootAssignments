// State Pattern for Vending Machine
interface State { void handle(); }
class VendingMachine {
    private State state;
    public void setState(State s) { state = s; }
    public void request() { state.handle(); }
}
public class StatePattern {
    public static void main(String[] args) {
        // Example usage
    }
}
