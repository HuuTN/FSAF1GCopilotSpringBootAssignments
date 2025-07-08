package observer;
// ConcreteObserver.java
// Concrete implementation of Observer
// Receives updates from Subject

public class ConcreteObserver implements Observer {
    private String name;
    public ConcreteObserver(String name) {
        this.name = name;
    }
    @Override
    public void update(String message) {
        System.out.println(name + " received: " + message);
    }
} 