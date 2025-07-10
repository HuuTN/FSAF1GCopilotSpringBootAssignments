import java.util.*;
public class Folder implements FSItem {
    private String name;
    private List<FSItem> items = new ArrayList<>();
    public Folder(String name) { this.name = name; }
    public void add(FSItem item) { items.add(item); }
    public void accept(FSVisitor v) {
        v.visit(this);
        for (FSItem i : items) i.accept(v);
    }
    public String getName() { return name; }
}
