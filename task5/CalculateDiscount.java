import java.util.List;

public class CalculateDiscount {

    public double calculateTotalWithDiscount(List<Integer> itemPrices, double discountPercentage) {
        double total = 0;
        for (int price : itemPrices) {
            total += price;
        }
        total -= (total * (discountPercentage / 100));
        return total;
    }

    // Extract the discount calculation into a separate private method
    private double applyDiscount(double total, double discountPercentage) {
        return total - (total * (discountPercentage / 100));
    }
}   