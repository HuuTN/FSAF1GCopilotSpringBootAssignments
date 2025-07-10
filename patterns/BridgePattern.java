// Bridge pattern example
abstract class ShapeBridge {
    protected DrawAPI drawAPI;
    protected ShapeBridge(DrawAPI drawAPI) { this.drawAPI = drawAPI; }
    public abstract void draw();
}
interface DrawAPI { void drawShape(); }
class RedDraw implements DrawAPI { public void drawShape() { System.out.println("Draw Red Shape"); } }
class CircleBridge extends ShapeBridge {
    public CircleBridge(DrawAPI drawAPI) { super(drawAPI); }
    public void draw() { drawAPI.drawShape(); }
}
