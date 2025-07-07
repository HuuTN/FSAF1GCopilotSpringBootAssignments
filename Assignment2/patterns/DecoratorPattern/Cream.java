public class Cream extends CoffeeDecorator {
    public Cream(Coffee c) { super(c); }
    public String getDesc() { return coffee.getDesc() + ", Cream"; }
    public int cost() { return coffee.cost() + 4; }
}
