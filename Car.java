//Create a Builder Pattern for constring a Car object with fileds brand , engineSize , color , and seatCount.
public class Car {
    private final String brand;
    private final double engineSize;
    private final String color;
    private final int seatCount;

    private Car(Builder builder) {
        this.brand = builder.brand;
        this.engineSize = builder.engineSize;
        this.color = builder.color;
        this.seatCount = builder.seatCount;
    }

    public String getBrand() {
        return brand;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public String getColor() {
        return color;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public static class Builder {
        private String brand;
        private double engineSize;
        private String color;
        private int seatCount;

        public Builder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder setEngineSize(double engineSize) {
            this.engineSize = engineSize;
            return this;
        }

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public Builder setSeatCount(int seatCount) {
            this.seatCount = seatCount;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}