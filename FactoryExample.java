// Factory Pattern Example
interface Product {
    void use();
}

class ConcreteProductA implements Product {
    public void use() {
        System.out.println("Using Product A");
    }
}

class ConcreteProductB implements Product {
    public void use() {
        System.out.println("Using Product B");
    }
}

class ProductFactory {
    public static Product createProduct(String type) {
        if (type.equals("A")) {
            return new ConcreteProductA();
        } else if (type.equals("B")) {
            return new ConcreteProductB();
        }
        throw new IllegalArgumentException("Unknown product type");
    }
}

public class FactoryExample {
    public static void main(String[] args) {
        Product productA = ProductFactory.createProduct("A");
        productA.use();
        Product productB = ProductFactory.createProduct("B");
        productB.use();
    }
}
