//Create a Factory Design Pattern in Java to create objects of type Shape (Circle, Square). Include necessary dimensions passed as arguments.
public class FactoryDesignPattern {

    // Shape interface
    interface Shape {
        void draw();
    }

    // Circle class implementing Shape
    static class Circle implements Shape {
        private double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        @Override
        public void draw() {
            System.out.println("Drawing a Circle with radius: " + radius);
        }
    }

    // Square class implementing Shape
    static class Square implements Shape {
        private double side;

        public Square(double side) {
            this.side = side;
        }

        @Override
        public void draw() {
            System.out.println("Drawing a Square with side: " + side);
        }
    }

    // Factory class to create shapes
    static class ShapeFactory {
        public static Shape createShape(String shapeType, double dimension) {
            if (shapeType.equalsIgnoreCase("Circle")) {
                return new Circle(dimension);
            } else if (shapeType.equalsIgnoreCase("Square")) {
                return new Square(dimension);
            }
            return null;
        }
    }

    // Main method to test the factory pattern
    public static void main(String[] args) {
        Shape circle = ShapeFactory.createShape("Circle", 5.0);
        circle.draw();

        Shape square = ShapeFactory.createShape("Square", 4.0);
        square.draw();
    }
}
