package patterns.structural.composite;

import java.util.List;

public interface Employee {
    String getName();
    String getPosition();
    void showDetails(String indent);
    List<Employee> getSubordinates();
    void addSubordinate(Employee e);
    void removeSubordinate(Employee e);
}
