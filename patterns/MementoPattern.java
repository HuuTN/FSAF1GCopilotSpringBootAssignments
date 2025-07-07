// Memento pattern example
class Memento {
    private String state;
    public Memento(String state) { this.state = state; }
    public String getState() { return state; }
}
class Originator {
    private String state;
    public void setState(String state) { this.state = state; }
    public Memento saveState() { return new Memento(state); }
    public void restoreState(Memento m) { this.state = m.getState(); }
}
