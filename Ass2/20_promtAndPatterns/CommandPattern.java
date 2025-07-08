// Command Pattern for Undo/Redo
import java.util.*;
interface Command { void execute(); void undo(); }
class TextEditor {
    private Stack<Command> history = new Stack<>();
    public void execute(Command c) { c.execute(); history.push(c); }
    public void undo() { if (!history.isEmpty()) history.pop().undo(); }
}
public class CommandPattern {
    public static void main(String[] args) {
        // Example usage
    }
}
