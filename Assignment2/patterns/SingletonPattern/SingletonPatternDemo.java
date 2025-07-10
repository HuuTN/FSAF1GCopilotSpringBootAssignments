public class SingletonPatternDemo {
    public static void main(String[] args) {
        SingletonPattern s1 = SingletonPattern.getInstance();
        SingletonPattern s2 = SingletonPattern.getInstance();
        System.out.println("s1 == s2? " + (s1 == s2)); // true
        s1.showMessage();
    }
}
