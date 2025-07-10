import java.util.*;
public class MenuGroup implements MenuComponent {
    private List<MenuComponent> items = new ArrayList<>();
    private String name;
    public MenuGroup(String name) { this.name = name; }
    public void add(MenuComponent c) { items.add(c); }
    public void show() {
        System.out.println("Menu: " + name);
        for (MenuComponent c : items) c.show();
    }
}
