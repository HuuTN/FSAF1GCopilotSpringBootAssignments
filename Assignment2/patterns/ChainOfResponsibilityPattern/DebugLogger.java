public class DebugLogger extends Logger {
    public DebugLogger() { level = DEBUG; }
    void write(String msg) { System.out.println("DEBUG: " + msg); }
}
