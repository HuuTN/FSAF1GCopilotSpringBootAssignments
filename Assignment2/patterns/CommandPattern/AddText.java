public class AddText implements Command {
    public void execute() { System.out.println("AddText"); }
    public void undo() { System.out.println("Undo AddText"); }
}
