public class DecoratorPatternDemo {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        coffee = new Sugar(coffee);
        coffee = new Milk(coffee);
        coffee = new Cream(coffee);
        System.out.println(coffee.getDesc() + ": " + coffee.cost());
    }
}
