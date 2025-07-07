import java.util.*;

interface ChatMediator {
    void sendMessage(String msg, User user);
    void addUser(User user);
}

class ChatRoom implements ChatMediator {
    private List<User> users = new ArrayList<>();
    public void addUser(User user) { users.add(user); }
    public void sendMessage(String msg, User user) {
        for (User u : users) {
            if (u != user) {
                u.receive(msg);
            }
        }
    }
}

abstract class User {
    protected ChatMediator mediator;
    protected String name;
    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }
    public abstract void send(String msg);
    public abstract void receive(String msg);
}

class ChatUser extends User {
    public ChatUser(ChatMediator mediator, String name) { super(mediator, name); }
    public void send(String msg) {
        System.out.println(this.name + " sends: " + msg);
        mediator.sendMessage(msg, this);
    }
    public void receive(String msg) {
        System.out.println(this.name + " receives: " + msg);
    }
}
