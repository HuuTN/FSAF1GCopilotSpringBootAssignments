public class Sugar extends CoffeeDecorator {
    public Sugar(Coffee c) { super(c); }
    public String getDesc() { return coffee.getDesc() + ", Sugar"; }
    public int cost() { return coffee.cost() + 2; }
}
