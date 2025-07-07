public class InfoLogger extends Logger {
    public InfoLogger() { level = INFO; }
    void write(String msg) { System.out.println("INFO: " + msg); }
}
