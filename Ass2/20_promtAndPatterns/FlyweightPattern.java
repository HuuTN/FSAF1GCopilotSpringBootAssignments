// Flyweight Pattern for Text Editor Characters
import java.util.*;
class Character {
    private char symbol;
    public Character(char s) { symbol = s; }
}
class CharacterFactory {
    private Map<Character, Character> pool = new HashMap<>();
    public Character get(char c) {
        pool.putIfAbsent(c, new Character(c));
        return pool.get(c);
    }
}
public class FlyweightPattern {
    public static void main(String[] args) {
        CharacterFactory factory = new CharacterFactory();
        Character a1 = factory.get('a');
        Character a2 = factory.get('a');
        System.out.println(a1 == a2); // true
    }
}
