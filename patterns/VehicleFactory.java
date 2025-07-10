// Factory Method pattern example
public class VehicleFactory {
    public static Vehicle createVehicle(String type) {
        switch(type) {
            case "car": return new Car();
            case "bike": return new Bike();
            default: throw new IllegalArgumentException("Unknown type");
        }
    }
}
class Vehicle {}
class Car extends Vehicle {}
class Bike extends Vehicle {}
