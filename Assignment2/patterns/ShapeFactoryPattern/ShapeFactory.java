public class ShapeFactory {
    public Shape getShape(String shapeType) {
        if (shapeType == null) return null;
        return switch (shapeType.toLowerCase()) {
            case "circle" -> new Circle();
            case "square" -> new Square();
            case "triangle" -> new Triangle();
            default -> null;
        };
    }
}
