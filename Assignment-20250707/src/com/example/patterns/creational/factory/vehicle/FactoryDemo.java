package patterns.creational.factory.vehicle;

public class FactoryDemo {
    public static void main(String[] args) {
        Vehicle car = VehicleFactory.createVehicle("car");
        Vehicle bike = VehicleFactory.createVehicle("bike");
        System.out.print("Car: ");
        car.drive();
        System.out.print("Bike: ");
        bike.drive();
    }
}
