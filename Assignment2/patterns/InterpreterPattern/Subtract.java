public class Subtract implements Expression {
    private Expression left, right;
    public Subtract(Expression l, Expression r) { left = l; right = r; }
    public int interpret() { return left.interpret() - right.interpret(); }
}
