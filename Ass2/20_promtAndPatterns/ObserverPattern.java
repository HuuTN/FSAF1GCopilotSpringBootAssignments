// Observer Pattern for Blog Updates
import java.util.*;
interface Observer { void update(String post); }
class User implements Observer { public void update(String post) { System.out.println("New post: " + post); } }
class Blog {
    private List<Observer> users = new ArrayList<>();
    public void subscribe(Observer o) { users.add(o); }
    public void notifyAll(String post) { for (Observer o : users) o.update(post); }
}
public class ObserverPattern {
    public static void main(String[] args) {
        Blog blog = new Blog();
        blog.subscribe(new User());
        blog.notifyAll("Hello World");
    }
}
