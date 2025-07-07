public class TemplateMethodPatternDemo {
    public static void main(String[] args) {
        CaffeineBeverage tea = new Tea();
        tea.prepare();
        CaffeineBeverage coffee = new Coffee();
        coffee.prepare();
    }
}
