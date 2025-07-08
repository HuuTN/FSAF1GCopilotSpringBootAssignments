// Bridge Pattern for Shape-Color
tinterface Color { String fill(); }
class Red implements Color { public String fill() { return "Red"; } }
abstract class Shape { protected Color color; public Shape(Color c) { color = c; } abstract void draw(); }
class Circle extends Shape { public Circle(Color c) { super(c); } public void draw() { System.out.println("Circle " + color.fill()); } }
public class BridgePattern {
    public static void main(String[] args) {
        Shape shape = new Circle(new Red());
        shape.draw();
    }
}
