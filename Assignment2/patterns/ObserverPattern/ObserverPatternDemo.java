public class ObserverPatternDemo {
    public static void main(String[] args) {
        WeatherStation ws = new WeatherStation();
        ws.add(new Display());
        ws.add(new AlertSystem());
        ws.notifyAll("Trời mưa!");
    }
}
