// Factory Pattern for creating shapes
interface Shape { void draw(); }
class Circle implements Shape { public void draw() { System.out.println("Circle"); } }
class Rectangle implements Shape { public void draw() { System.out.println("Rectangle"); } }
class Square implements Shape { public void draw() { System.out.println("Square"); } }
public class FactoryPattern {
    public Shape getShape(String type) {
        switch(type) {
            case "Circle": return new Circle();
            case "Rectangle": return new Rectangle();
            case "Square": return new Square();
            default: return null;
        }
    }
}
