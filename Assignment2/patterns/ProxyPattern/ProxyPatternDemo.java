public class ProxyPatternDemo {
    public static void main(String[] args) {
        Image img = new ProxyImage("big_image.jpg");
        img.display();
        img.display();
    }
}
