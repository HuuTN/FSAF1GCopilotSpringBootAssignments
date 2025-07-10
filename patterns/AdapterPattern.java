// Adapter pattern example
interface Target {
    void request();
}
class Adaptee {
    void specificRequest() { System.out.println("Specific request"); }
}
class Adapter implements Target {
    private Adaptee adaptee;
    public Adapter(Adaptee adaptee) { this.adaptee = adaptee; }
    public void request() { adaptee.specificRequest(); }
}
