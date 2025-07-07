public class FlyweightPatternDemo {
    public static void main(String[] args) {
        GlyphFactory factory = new GlyphFactory();
        String text = "hello world";
        for (char c : text.toCharArray()) {
            factory.get(c).draw();
        }
        System.out.println();
    }
}
