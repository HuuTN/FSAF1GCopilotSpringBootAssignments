// Mediator pattern example
interface ChatMediator {
    void sendMessage(String msg, User user);
}
class ChatRoom implements ChatMediator {
    public void sendMessage(String msg, User user) {
        System.out.println(user.getName() + ": " + msg);
    }
}
class User {
    private String name;
    private ChatMediator mediator;
    public User(String name, ChatMediator mediator) { this.name = name; this.mediator = mediator; }
    public String getName() { return name; }
    public void send(String msg) { mediator.sendMessage(msg, this); }
}
