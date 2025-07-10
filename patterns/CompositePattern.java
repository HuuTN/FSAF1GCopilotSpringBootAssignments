// Composite pattern example
import java.util.*;
interface Component {
    void showDetails();
}
class Leaf implements Component {
    private String name;
    public Leaf(String name) { this.name = name; }
    public void showDetails() { System.out.println(name); }
}
class Composite implements Component {
    private List<Component> children = new ArrayList<>();
    public void add(Component c) { children.add(c); }
    public void showDetails() { for (Component c : children) c.showDetails(); }
}
