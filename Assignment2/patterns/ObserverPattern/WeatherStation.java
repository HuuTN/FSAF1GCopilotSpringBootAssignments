import java.util.*;
public class WeatherStation {
    private List<Observer> observers = new ArrayList<>();
    public void add(Observer o) { observers.add(o); }
    public void notifyAll(String msg) { for (Observer o : observers) o.update(msg); }
}
