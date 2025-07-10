package patterns.structural.composite;

import java.util.Collections;
import java.util.List;

public class Developer implements Employee {
    private String name;
    private String position;

    public Developer(String name, String position) {
        this.name = name;
        this.position = position;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + position + ": " + name);
    }

    @Override
    public List<Employee> getSubordinates() {
        return Collections.emptyList();
    }

    @Override
    public void addSubordinate(Employee e) {
        // Leaf node, do nothing
    }

    @Override
    public void removeSubordinate(Employee e) {
        // Leaf node, do nothing
    }
}
