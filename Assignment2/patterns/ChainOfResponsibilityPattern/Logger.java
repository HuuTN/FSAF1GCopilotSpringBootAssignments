public abstract class Logger {
    public static int INFO = 1, DEBUG = 2, ERROR = 3;
    protected int level;
    protected Logger next;
    public void setNext(Logger n) { next = n; }
    public void log(int lvl, String msg) {
        if (level <= lvl) write(msg);
        if (next != null) next.log(lvl, msg);
    }
    abstract void write(String msg);
}
