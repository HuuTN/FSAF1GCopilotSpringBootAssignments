// Memento Pattern for Text Editor State
class Memento { private String state; public Memento(String s) { state = s; } public String getState() { return state; } }
class Editor {
    private String text;
    public Memento save() { return new Memento(text); }
    public void restore(Memento m) { text = m.getState(); }
}
public class MementoPattern {
    public static void main(String[] args) {
        // Example usage
    }
}
