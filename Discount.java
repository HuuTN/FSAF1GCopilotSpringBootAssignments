import java.util.List;

public class Discount {

    //Create method calculateTotalWithDiscount that takes a list of item prices and a discount percentage
    public double calculateTotalWithDiscount(List<Integer> itemPrices, double discountPercentage) {
        double total = 0;
        for (int price : itemPrices) {
            total += price;
        }
        return applyDiscount(total, discountPercentage);
    }

    //Extract method discount calculation intp a separate private method
    private double applyDiscount(double total, double discountPercentage) {
        return total - (total * (discountPercentage / 100));
    }

}
