import java.util.*;

class TextEditor {
    private String text = "";
    private Stack<Memento> undoStack = new Stack<>();
    private Stack<Memento> redoStack = new Stack<>();

    public void type(String newText) {
        undoStack.push(new Memento(text));
        text += newText;
        redoStack.clear();
    }
    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(new Memento(text));
            text = undoStack.pop().getState();
        }
    }
    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(new Memento(text));
            text = redoStack.pop().getState();
        }
    }
    public String getText() { return text; }
    private static class Memento {
        private final String state;
        public Memento(String state) { this.state = state; }
        public String getState() { return state; }
    }
}
