public class InterpreterPatternDemo {
    public static void main(String[] args) {
        // Biểu thức: 5 + 3 * 2 - 1
        Expression expr = new Subtract(
            new Add(
                new Number(5),
                new Multiply(new Number(3), new Number(2))
            ),
            new Number(1)
        );
        System.out.println(expr.interpret()); // Output: 10
    }
}
