//Create a thread-safe counter class that uses synchronized methods for incrementing and decrementing a count.
public class Counters {
    private int count;

    public Counters() {
        this.count = 0;
    }

    public synchronized void increment() {
        count++;
    }

    public synchronized void decrement() {
        count--;
    }

    public synchronized int getCount() {
        return count;
    }
}