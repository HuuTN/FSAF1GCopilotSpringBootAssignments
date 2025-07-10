import java.util.ArrayList;
import java.util.List;

interface Graphic {
    void draw();
}

class Circle implements Graphic {
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

class CompositeGraphic implements Graphic {
    private List<Graphic> children = new ArrayList<>();
    public void add(Graphic g) { children.add(g); }
    public void remove(Graphic g) { children.remove(g); }
    public void draw() {
        for (Graphic g : children) {
            g.draw();
        }
    }
}
