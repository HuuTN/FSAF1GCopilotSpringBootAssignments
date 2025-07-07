public class Add implements Expression {
    private Expression left, right;
    public Add(Expression l, Expression r) { left = l; right = r; }
    public int interpret() { return left.interpret() + right.interpret(); }
}
