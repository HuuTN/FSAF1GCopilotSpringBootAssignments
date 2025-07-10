// State pattern example
interface State {
    void handle();
}
class GreenLight implements State {
    public void handle() { System.out.println("Green Light"); }
}
class RedLight implements State {
    public void handle() { System.out.println("Red Light"); }
}
class TrafficLight {
    private State state;
    public void setState(State state) { this.state = state; }
    public void request() { state.handle(); }
}
