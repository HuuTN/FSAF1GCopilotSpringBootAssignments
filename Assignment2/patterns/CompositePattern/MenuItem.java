public class MenuItem implements MenuComponent {
    private String name;
    public MenuItem(String n) { name = n; }
    public void show() { System.out.println(name); }
}
