import java.util.*;
public class GlyphFactory {
    private Map<Character, Glyph> pool = new HashMap<>();
    public Glyph get(char c) {
        pool.putIfAbsent(c, new CharacterGlyph(c));
        return pool.get(c);
    }
}
