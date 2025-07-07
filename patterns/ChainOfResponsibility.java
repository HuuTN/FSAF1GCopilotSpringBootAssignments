// Chain of Responsibility pattern example
abstract class Handler {
    protected Handler next;
    public void setNext(Handler next) { this.next = next; }
    public abstract void handle(String request);
}
class ConcreteHandlerA extends Handler {
    public void handle(String request) {
        if (request.equals("A")) System.out.println("Handled by A");
        else if (next != null) next.handle(request);
    }
}
class ConcreteHandlerB extends Handler {
    public void handle(String request) {
        if (request.equals("B")) System.out.println("Handled by B");
        else if (next != null) next.handle(request);
    }
}
