// Proxy pattern example
interface Image {
    void display();
}
class RealImage implements Image {
    private String filename;
    public RealImage(String filename) { this.filename = filename; }
    public void display() { System.out.println("Displaying " + filename); }
}
class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;
    public ProxyImage(String filename) { this.filename = filename; }
    public void display() {
        if (realImage == null) realImage = new RealImage(filename);
        realImage.display();
    }
}
