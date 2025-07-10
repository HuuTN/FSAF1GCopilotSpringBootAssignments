public class FormMediator implements Mediator {
    public void notify(Component c, String event) { System.out.println("Event: " + event); }
}
