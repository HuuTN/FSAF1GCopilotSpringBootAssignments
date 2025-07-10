package patterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class Manager implements Employee {
    private String name;
    private String position;
    private List<Employee> subordinates = new ArrayList<>();

    public Manager(String name, String position) {
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
        for (Employee e : subordinates) {
            e.showDetails(indent + "    ");
        }
    }

    @Override
    public List<Employee> getSubordinates() {
        return subordinates;
    }

    @Override
    public void addSubordinate(Employee e) {
        subordinates.add(e);
    }

    @Override
    public void removeSubordinate(Employee e) {
        subordinates.remove(e);
    }
}
