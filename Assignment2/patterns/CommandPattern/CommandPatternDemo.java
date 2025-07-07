public class CommandPatternDemo {
    public static void main(String[] args) {
        Editor editor = new Editor();
        editor.execute(new AddText());
        editor.execute(new DeleteText());
        editor.undo();
        editor.execute(new ReplaceText());
        editor.undo();
    }
}
