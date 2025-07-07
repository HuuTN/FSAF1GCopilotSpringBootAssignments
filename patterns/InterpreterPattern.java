// Interpreter pattern example
import java.util.*;
interface Expression {
    int interpret(Map<String, Integer> context);
}
class Number implements Expression {
    private int number;
    public Number(int number) { this.number = number; }
    public int interpret(Map<String, Integer> context) { return number; }
}
class Plus implements Expression {
    private Expression left, right;
    public Plus(Expression left, Expression right) { this.left = left; this.right = right; }
    public int interpret(Map<String, Integer> context) { return left.interpret(context) + right.interpret(context); }
}
