public class Editor {
    private String text;
    public void setText(String t) { text = t; }
    public EditorMemento save() { return new EditorMemento(text); }
    public void restore(EditorMemento m) { text = m.getState(); }
    public String getText() { return text; }
}
