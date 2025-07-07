public class TextBox implements Component {
    private Mediator m;
    public void setMediator(Mediator m) { this.m = m; }
    public void input(String text) { m.notify(this, "TextBox input: " + text); }
}
