interface Animal {
    void speak();
}

class Dog implements Animal {
    public void speak() {
        System.out.println("Woof!");
    }
}

class Cat implements Animal {
    public void speak() {
        System.out.println("Meow!");
    }
}

class AnimalFactory {
    public static Animal getAnimal(String type) {
        if ("dog".equalsIgnoreCase(type)) return new Dog();
        if ("cat".equalsIgnoreCase(type)) return new Cat();
        return null;
    }
}

public class FactoryExample {
    public static void main(String[] args) {
        Animal animal1 = AnimalFactory.getAnimal("dog");
        animal1.speak();
        Animal animal2 = AnimalFactory.getAnimal("cat");
        animal2.speak();
    }
}