public class ErrorLogger extends Logger {
    public ErrorLogger() { level = ERROR; }
    void write(String msg) { System.out.println("ERROR: " + msg); }
}
