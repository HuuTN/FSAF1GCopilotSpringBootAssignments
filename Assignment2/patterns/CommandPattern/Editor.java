import java.util.*;
public class Editor {
    Stack<Command> history = new Stack<>();
    public void execute(Command c) { c.execute(); history.push(c); }
    public void undo() { if (!history.isEmpty()) history.pop().undo(); }
}
