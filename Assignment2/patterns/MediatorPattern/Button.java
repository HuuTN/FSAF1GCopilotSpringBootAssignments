public class Button implements Component {
    private Mediator m;
    public void setMediator(Mediator m) { this.m = m; }
    public void click() { m.notify(this, "Button clicked"); }
}
