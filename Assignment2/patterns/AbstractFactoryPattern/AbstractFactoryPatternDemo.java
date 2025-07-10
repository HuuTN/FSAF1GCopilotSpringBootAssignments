public class AbstractFactoryPatternDemo {
    public static void main(String[] args) {
        UIFactory winFactory = new WinFactory();
        Button winBtn = winFactory.createButton();
        Checkbox winCb = winFactory.createCheckbox();
        winBtn.paint();
        winCb.paint();

        UIFactory macFactory = new MacFactory();
        Button macBtn = macFactory.createButton();
        Checkbox macCb = macFactory.createCheckbox();
        macBtn.paint();
        macCb.paint();
    }
}
