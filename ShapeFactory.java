// Abstract Shape class
abstract class Shape {
    public abstract double area();
}

// Circle class
class Circle extends Shape {
    private final double radius;
    public Circle(double radius) {
        this.radius = radius;
    }
    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

// Square class
class Square extends Shape {
    private final double side;
    public Square(double side) {
        this.side = side;
    }
    @Override
    public double area() {
        return side * side;
    }
}

// ShapeFactory class
public class ShapeFactory {
    public static Shape createShape(String type, double dimension) {
        if ("circle".equalsIgnoreCase(type)) {
            return new Circle(dimension);
        } else if ("square".equalsIgnoreCase(type)) {
            return new Square(dimension);
        } else {
            throw new IllegalArgumentException("Unknown shape type: " + type);
        }
    }
}
