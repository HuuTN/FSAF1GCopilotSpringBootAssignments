import java.util.*;

interface Observer {
    void update(float temp, float humidity, float pressure);
}

interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

class WeatherData implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private float temperature;
    private float humidity;
    private float pressure;

    public void registerObserver(Observer o) { observers.add(o); }
    public void removeObserver(Observer o) { observers.remove(o); }
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(temperature, humidity, pressure);
        }
    }
    public void setMeasurements(float t, float h, float p) {
        this.temperature = t;
        this.humidity = h;
        this.pressure = p;
        notifyObservers();
    }
}

class CurrentConditionsDisplay implements Observer {
    public void update(float temp, float humidity, float pressure) {
        System.out.println("Current conditions: " + temp + "C, " + humidity + "% humidity");
    }
}

class ForecastDisplay implements Observer {
    public void update(float temp, float humidity, float pressure) {
        System.out.println("Forecast: pressure " + pressure);
    }
}
