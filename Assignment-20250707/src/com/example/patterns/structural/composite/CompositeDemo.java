package patterns.structural.composite;

public class CompositeDemo {
    public static void main(String[] args) {
        Manager manager = new Manager("Alice", "Manager");
        Developer dev1 = new Developer("Bob", "Developer");
        Developer dev2 = new Developer("Charlie", "Developer");
        manager.addSubordinate(dev1);
        manager.addSubordinate(dev2);
        manager.showDetails("");
    }
}
