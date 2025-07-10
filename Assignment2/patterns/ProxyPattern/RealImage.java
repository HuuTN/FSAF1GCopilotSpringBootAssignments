public class RealImage implements Image {
    private String filename;
    public RealImage(String f) { filename = f; load(); }
    private void load() { System.out.println("Loading " + filename); }
    public void display() { System.out.println("Displaying " + filename); }
}
