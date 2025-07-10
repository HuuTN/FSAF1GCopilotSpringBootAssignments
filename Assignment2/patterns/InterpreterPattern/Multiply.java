public class Multiply implements Expression {
    private Expression left, right;
    public Multiply(Expression l, Expression r) { left = l; right = r; }
    public int interpret() { return left.interpret() * right.interpret(); }
}
