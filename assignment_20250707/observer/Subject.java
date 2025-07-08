package observer;
// Subject.java
// Subject class in Observer pattern
// Maintains a list of observers and notifies them of changes

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observers = new ArrayList<>();
    public void addObserver(Observer o) { observers.add(o); }
    public void removeObserver(Observer o) { observers.remove(o); }
    public void notifyObservers(String message) {
        for (Observer o : observers) o.update(message);
    }
}

// Usage example:
// Subject subject = new Subject();
// Observer obs1 = new ConcreteObserver("Observer1");
// subject.addObserver(obs1);
// subject.notifyObservers("Hello!"); 