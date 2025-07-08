//Generate a toString method for the Employee class that formats id , name , and email into a single string.
public class Employee {
    private final int id;
    private final String name;
    private final String email;

    public Employee(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}