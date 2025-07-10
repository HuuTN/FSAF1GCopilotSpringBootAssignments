// Flyweight pattern example
import java.util.*;
class Character {
    private char symbol;
    public Character(char symbol) { this.symbol = symbol; }
}
class CharacterFactory {
    private Map<Character, Character> pool = new HashMap<>();
    public Character getCharacter(char symbol) {
        pool.putIfAbsent(symbol, new Character(symbol));
        return pool.get(symbol);
    }
}
