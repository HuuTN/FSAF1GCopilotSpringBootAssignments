public class Pizza {
    private final String size;
    private final boolean cheese;
    private final boolean pepperoni;
    private final boolean bacon;
    private final boolean mushrooms;
    private final boolean olives;

    private Pizza(Builder builder) {
        this.size = builder.size;
        this.cheese = builder.cheese;
        this.pepperoni = builder.pepperoni;
        this.bacon = builder.bacon;
        this.mushrooms = builder.mushrooms;
        this.olives = builder.olives;
    }

    public String getSize() {
        return size;
    }

    public boolean hasCheese() {
        return cheese;
    }

    public boolean hasPepperoni() {
        return pepperoni;
    }

    public boolean hasBacon() {
        return bacon;
    }

    public boolean hasMushrooms() {
        return mushrooms;
    }

    public boolean hasOlives() {
        return olives;
    }

    public static class Builder {
        private final String size;
        private boolean cheese = false;
        private boolean pepperoni = false;
        private boolean bacon = false;
        private boolean mushrooms = false;
        private boolean olives = false;

        public Builder(String size) {
            this.size = size;
        }

        public Builder cheese() {
            this.cheese = true;
            return this;
        }

        public Builder pepperoni() {
            this.pepperoni = true;
            return this;
        }

        public Builder bacon() {
            this.bacon = true;
            return this;
        }

        public Builder mushrooms() {
            this.mushrooms = true;
            return this;
        }

        public Builder olives() {
            this.olives = true;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }
}
