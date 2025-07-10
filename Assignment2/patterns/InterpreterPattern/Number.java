public class Number implements Expression {
    private int value;
    public Number(int v) { value = v; }
    public int interpret() { return value; }
}
