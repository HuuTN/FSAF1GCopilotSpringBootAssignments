// Builder Pattern for Pizza
public class BuilderPattern {
    public static class Pizza {
        private String crust;
        private String toppings;
        private Pizza(Builder b) {
            this.crust = b.crust;
            this.toppings = b.toppings;
        }
        public String toString() { return "Pizza with " + crust + ", " + toppings; }
        public static class Builder {
            private String crust;
            private String toppings;
            public Builder crust(String c) { this.crust = c; return this; }
            public Builder toppings(String t) { this.toppings = t; return this; }
            public Pizza build() { return new Pizza(this); }
        }
    }
    public static void main(String[] args) {
        Pizza pizza = new Pizza.Builder().crust("Thin").toppings("Cheese, Pepperoni").build();
        System.out.println(pizza);
    }
}
