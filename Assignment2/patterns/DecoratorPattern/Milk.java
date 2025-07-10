public class Milk extends CoffeeDecorator {
    public Milk(Coffee c) { super(c); }
    public String getDesc() { return coffee.getDesc() + ", Milk"; }
    public int cost() { return coffee.cost() + 3; }
}
