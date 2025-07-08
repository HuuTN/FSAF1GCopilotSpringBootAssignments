// Abstract Factory Pattern for UI components
interface Button { void paint(); }
class WinButton implements Button { public void paint() { System.out.println("WinButton"); } }
class MacButton implements Button { public void paint() { System.out.println("MacButton"); } }
interface UIFactory { Button createButton(); }
class WinFactory implements UIFactory { public Button createButton() { return new WinButton(); } }
class MacFactory implements UIFactory { public Button createButton() { return new MacButton(); } }
public class AbstractFactoryPattern {
    public static void main(String[] args) {
        UIFactory factory = new WinFactory();
        Button button = factory.createButton();
        button.paint();
    }
}
