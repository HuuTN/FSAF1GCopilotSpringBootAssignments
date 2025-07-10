public class CheckBox implements Component {
    private Mediator m;
    public void setMediator(Mediator m) { this.m = m; }
    public void check() { m.notify(this, "CheckBox checked"); }
}
