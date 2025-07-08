// Template Method Pattern for Game AI
abstract class GameAI {
    public final void turn() { collectResources(); buildStructures(); buildUnits(); attack(); }
    protected abstract void collectResources();
    protected abstract void buildStructures();
    protected abstract void buildUnits();
    protected void attack() { System.out.println("Attack!"); }
}
public class TemplateMethodPattern {
    public static void main(String[] args) {
        // Example usage
    }
}
