public class MediatorPatternDemo {
    public static void main(String[] args) {
        FormMediator mediator = new FormMediator();
        TextBox tb = new TextBox();
        CheckBox cb = new CheckBox();
        Button btn = new Button();
        tb.setMediator(mediator);
        cb.setMediator(mediator);
        btn.setMediator(mediator);
        tb.input("user");
        cb.check();
        btn.click();
    }
}
