// Observer pattern example
import java.util.*;
interface Observer { void update(String msg); }
class Subject {
    private List<Observer> observers = new ArrayList<>();
    public void addObserver(Observer o) { observers.add(o); }
    public void notifyObservers(String msg) { for (Observer o : observers) o.update(msg); }
}
class ConcreteObserver implements Observer {
    private String name;
    public ConcreteObserver(String name) { this.name = name; }
    public void update(String msg) { System.out.println(name + " received: " + msg); }
}
