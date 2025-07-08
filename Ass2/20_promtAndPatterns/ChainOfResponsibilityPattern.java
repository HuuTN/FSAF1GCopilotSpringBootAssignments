// Chain of Responsibility Pattern for Logger
abstract class Logger {
    protected Logger next;
    public void setNext(Logger n) { next = n; }
    public void log(String msg, int level) {
        if (canHandle(level)) handle(msg);
        else if (next != null) next.log(msg, level);
    }
    protected abstract boolean canHandle(int level);
    protected abstract void handle(String msg);
}
public class ChainOfResponsibilityPattern {
    public static void main(String[] args) {
        // Example usage
    }
}
