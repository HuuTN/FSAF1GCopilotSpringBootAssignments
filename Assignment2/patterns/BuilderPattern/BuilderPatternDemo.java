public class BuilderPatternDemo {
    public static void main(String[] args) {
        House house = new HouseBuilder().garden().pool().build();
        System.out.println(house);
    }
}
