abstract class Logger {
    public static int CONSOLE = 1;
    public static int FILE = 2;
    public static int EMAIL = 3;
    protected int level;
    protected Logger nextLogger;
    public void setNextLogger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }
    public void logMessage(int level, String message) {
        if (this.level <= level) {
            write(message);
        }
        if (nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
    }
    protected abstract void write(String message);
}

class ConsoleLogger extends Logger {
    public ConsoleLogger(int level) { this.level = level; }
    protected void write(String message) {
        System.out.println("Console: " + message);
    }
}

class FileLogger extends Logger {
    public FileLogger(int level) { this.level = level; }
    protected void write(String message) {
        System.out.println("File: " + message);
    }
}

class EmailLogger extends Logger {
    public EmailLogger(int level) { this.level = level; }
    protected void write(String message) {
        System.out.println("Email: " + message);
    }
}
