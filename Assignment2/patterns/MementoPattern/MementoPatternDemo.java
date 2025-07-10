public class MementoPatternDemo {
    public static void main(String[] args) {
        Editor editor = new Editor();
        editor.setText("Hello");
        EditorMemento m1 = editor.save();
        editor.setText("Hello World");
        EditorMemento m2 = editor.save();
        editor.setText("Hi");
        System.out.println(editor.getText());
        editor.restore(m2);
        System.out.println(editor.getText());
        editor.restore(m1);
        System.out.println(editor.getText());
    }
}
