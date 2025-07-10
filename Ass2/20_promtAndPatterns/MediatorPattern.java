// Mediator Pattern for Chatroom
import java.util.*;
interface Mediator { void send(String msg, User user); }
class ChatRoom implements Mediator {
    private List<User> users = new ArrayList<>();
    public void addUser(User u) { users.add(u); }
    public void send(String msg, User user) {
        for (User u : users) if (u != user) u.receive(msg);
    }
}
class User {
    private Mediator mediator; public User(Mediator m) { mediator = m; }
    public void send(String msg) { mediator.send(msg, this); }
    public void receive(String msg) { System.out.println("Received: " + msg); }
}
public class MediatorPattern {
    public static void main(String[] args) {
        // Example usage
    }
}
