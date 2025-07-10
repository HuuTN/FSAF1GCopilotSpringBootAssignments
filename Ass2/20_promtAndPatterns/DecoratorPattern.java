// Decorator Pattern for Coffee
interface Coffee { String getDescription(); double cost(); }
class SimpleCoffee implements Coffee { public String getDescription() { return "Coffee"; } public double cost() { return 5; } }
class MilkDecorator implements Coffee {
    private Coffee coffee;
    public MilkDecorator(Coffee c) { coffee = c; }
    public String getDescription() { return coffee.getDescription() + ", Milk"; }
    public double cost() { return coffee.cost() + 1; }
}
public class DecoratorPattern {
    public static void main(String[] args) {
        Coffee coffee = new MilkDecorator(new SimpleCoffee());
        System.out.println(coffee.getDescription() + ": " + coffee.cost());
    }
}
