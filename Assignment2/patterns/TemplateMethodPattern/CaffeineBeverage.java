public abstract class CaffeineBeverage {
    final void prepare() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }
    void boilWater() { System.out.println("Boiling water"); }
    abstract void brew();
    void pourInCup() { System.out.println("Pouring in cup"); }
    abstract void addCondiments();
}
