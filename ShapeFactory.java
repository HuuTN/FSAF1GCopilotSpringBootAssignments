// ShapeFactory.java

interface Shape {
    double area();
    double perimeter();
}

class Circle implements Shape {
    private final double radius;
    public Circle(double radius) {
        this.radius = radius;
    }
    public double area() {
        return Math.PI * radius * radius;
    }
    public double perimeter() {
        return 2 * Math.PI * radius;
    }
    public String toString() {
        return "Circle{radius=" + radius + "}";
    }
}

class Square implements Shape {
    private final double side;
    public Square(double side) {
        this.side = side;
    }
    public double area() {
        return side * side;
    }
    public double perimeter() {
        return 4 * side;
    }
    public String toString() {
        return "Square{side=" + side + "}";
    }
}

public class ShapeFactory {
    public static Shape createCircle(double radius) {
        return new Circle(radius);
    }
    public static Shape createSquare(double side) {
        return new Square(side);
    }
    // Example usage
    public static void main(String[] args) {
        Shape c = ShapeFactory.createCircle(3.0);
        Shape s = ShapeFactory.createSquare(4.0);
        System.out.println(c + ", area=" + c.area() + ", perimeter=" + c.perimeter());
        System.out.println(s + ", area=" + s.area() + ", perimeter=" + s.perimeter());
    }
}
