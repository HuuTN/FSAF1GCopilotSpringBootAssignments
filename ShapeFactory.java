// Shape interface
interface Shape {
    void draw();
    double getArea();
}

// Circle class implementing Shape
class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing Circle with radius: " + radius);
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}

// Square class implementing Shape
class Square implements Shape {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public void draw() {
        System.out.println("Drawing Square with side length: " + side);
    }

    @Override
    public double getArea() {
        return side * side;
    }
}

// ShapeFactory class to create Shape objects
public class ShapeFactory {
    // Factory method to get shape object
    public static Shape getShape(String shapeType, double... dimensions) {
        if (shapeType == null) {
            return null;
        }

        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            if (dimensions.length > 0) {
                return new Circle(dimensions[0]); // dimensions[0] is radius
            }
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            if (dimensions.length > 0) {
                return new Square(dimensions[0]); // dimensions[0] is side length
            }
        }

        return null;
    }

    // Main method to demonstrate the factory pattern
    public static void main(String[] args) {
        // Create a circle with radius 5
        Shape circle = ShapeFactory.getShape("CIRCLE", 5.0);
        System.out.println("Circle Area: " + circle.getArea());
        circle.draw();

        // Create a square with side 4
        Shape square = ShapeFactory.getShape("SQUARE", 4.0);
        System.out.println("Square Area: " + square.getArea());
        square.draw();
    }
}
