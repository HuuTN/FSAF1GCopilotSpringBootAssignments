
// Shape interface
interface Shape {
    double area();
}

// Circle class
class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

// Square class
class Square implements Shape {
    private double side;

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
    public static Shape createShape(String type, double... dimensions) {
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }
        switch (type.toLowerCase()) {
            case "circle":
                if (dimensions.length != 1) {
                    throw new IllegalArgumentException("Circle requires 1 dimension (radius)");
                }
                return new Circle(dimensions[0]);
            case "square":
                if (dimensions.length != 1) {
                    throw new IllegalArgumentException("Square requires 1 dimension (side)");
                }
                return new Square(dimensions[0]);
            default:
                throw new IllegalArgumentException("Unknown shape type: " + type);
        }
    }
}
