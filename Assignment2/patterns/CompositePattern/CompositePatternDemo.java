public class CompositePatternDemo {
    public static void main(String[] args) {
        MenuGroup mainMenu = new MenuGroup("Main");
        mainMenu.add(new MenuItem("Home"));
        MenuGroup subMenu = new MenuGroup("Settings");
        subMenu.add(new MenuItem("Profile"));
        subMenu.add(new MenuItem("Security"));
        mainMenu.add(subMenu);
        mainMenu.show();
    }
}
