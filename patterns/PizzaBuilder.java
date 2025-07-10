// Builder pattern example
public class Pizza {
    private String dough;
    private String topping;
    private Pizza(Builder builder) {
        this.dough = builder.dough;
        this.topping = builder.topping;
    }
    public static class Builder {
        private String dough;
        private String topping;
        public Builder dough(String dough) { this.dough = dough; return this; }
        public Builder topping(String topping) { this.topping = topping; return this; }
        public Pizza build() { return new Pizza(this); }
    }
}
