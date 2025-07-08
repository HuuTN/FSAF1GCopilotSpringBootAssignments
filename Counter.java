// Not need main method
// Create a thread-safe counter class that uses synchronized methods for incrementing and retrieving the count.
public class Counter {
    private int count = 0;

    // Synchronized method to increment the count
    public synchronized void increment() {
        count++;
    }

    // Synchronized method to retrieve the current count
    public synchronized int getCount() {
        return count;
    }
}