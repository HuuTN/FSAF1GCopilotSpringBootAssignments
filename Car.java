public class Car {
    private final String brand;
    private final double engineSize;
    private final int seatCount;
    private final String color; 
    
    private Car(CarBuilder builder) {
        this.brand = builder.brand;
        this.engineSize = builder.engineSize;
        this.seatCount = builder.seatCount;
        this.color = builder.color;
    }

    public String getBrand() {
        return brand;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public String getColor() {
        return color;
    }

    public static class CarBuilder {
        private String brand;
        private double engineSize;
        private int seatCount;
        private String color;

        public CarBuilder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public CarBuilder engineSize(double engineSize) {
            this.engineSize = engineSize;
            return this;
        }

        public CarBuilder seatCount(int seatCount) {
            this.seatCount = seatCount;
            return this;
        }

        public CarBuilder color(String color) {
            this.color = color;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }

    public static CarBuilder builder() {
        return new CarBuilder();
    }
}
