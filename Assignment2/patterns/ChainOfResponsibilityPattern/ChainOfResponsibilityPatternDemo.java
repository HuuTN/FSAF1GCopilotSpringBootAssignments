public class ChainOfResponsibilityPatternDemo {
    public static void main(String[] args) {
        Logger info = new InfoLogger();
        Logger debug = new DebugLogger();
        Logger error = new ErrorLogger();
        info.setNext(debug); debug.setNext(error);
        info.log(Logger.INFO, "Thông tin");
        info.log(Logger.DEBUG, "Gỡ lỗi");
        info.log(Logger.ERROR, "Lỗi nghiêm trọng");
    }
}
