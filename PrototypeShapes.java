abstract class Shape implements Cloneable {
    private String id;
    private String type;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public abstract void draw();

    public Shape clone() {
        try {
            return (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

class Circle extends Shape {
    public Circle() { setType("Circle"); }
    public void draw() { System.out.println("Drawing Circle"); }
}

class Rectangle extends Shape {
    public Rectangle() { setType("Rectangle"); }
    public void draw() { System.out.println("Drawing Rectangle"); }
}

class ShapeCache {
    private static java.util.Map<String, Shape> shapeMap = new java.util.HashMap<>();

    public static void loadCache() {
        Circle circle = new Circle();
        circle.setId("1");
        shapeMap.put(circle.getId(), circle);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("2");
        shapeMap.put(rectangle.getId(), rectangle);
    }

    public static Shape getShape(String shapeId) {
        Shape cachedShape = shapeMap.get(shapeId);
        return cachedShape != null ? cachedShape.clone() : null;
    }
}
