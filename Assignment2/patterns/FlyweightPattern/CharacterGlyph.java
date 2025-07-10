public class CharacterGlyph implements Glyph {
    private char c;
    public CharacterGlyph(char c) { this.c = c; }
    public void draw() { System.out.print(c); }
}
