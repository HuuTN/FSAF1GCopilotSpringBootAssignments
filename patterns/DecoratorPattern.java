// Decorator pattern example
interface Coffee {
    String getDescription();
}
class SimpleCoffee implements Coffee {
    public String getDescription() { return "Simple Coffee"; }
}
class MilkDecorator implements Coffee {
    private Coffee coffee;
    public MilkDecorator(Coffee coffee) { this.coffee = coffee; }
    public String getDescription() { return coffee.getDescription() + ", Milk"; }
}
