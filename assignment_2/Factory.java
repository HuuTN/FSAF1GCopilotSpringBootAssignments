package assignment_2.factory;

// Product interface
public interface Product {
    void create();
}

// Concrete Product A
class ProductA implements Product {
    @Override
    public void create() {
        System.out.println("Product A created.");
    }
}

// Concrete Product B
class ProductB implements Product {
    @Override
    public void create() {
        System.out.println("Product B created.");
    }
}

// Factory class
public class Factory {
    public static Product getProduct(String type) {
        if (type.equalsIgnoreCase("A")) {
            return new ProductA();
        } else if (type.equalsIgnoreCase("B")) {
            return new ProductB();
        }
        return null;
    }
}