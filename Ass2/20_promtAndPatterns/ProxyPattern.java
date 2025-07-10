// Proxy Pattern for Virtual Proxy Image
interface Image { void display(); }
class RealImage implements Image {
    private String filename;
    public RealImage(String f) { filename = f; load(); }
    private void load() { System.out.println("Loading " + filename); }
    public void display() { System.out.println("Displaying " + filename); }
}
class ProxyImage implements Image {
    private RealImage real; private String filename;
    public ProxyImage(String f) { filename = f; }
    public void display() {
        if (real == null) real = new RealImage(filename);
        real.display();
    }
}
public class ProxyPattern {
    public static void main(String[] args) {
        Image img = new ProxyImage("test.jpg");
        img.display();
    }
}
