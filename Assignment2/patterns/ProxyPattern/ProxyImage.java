public class ProxyImage implements Image {
    private RealImage real;
    private String filename;
    public ProxyImage(String f) { filename = f; }
    public void display() {
        if (real == null) real = new RealImage(filename);
        real.display();
    }
}
