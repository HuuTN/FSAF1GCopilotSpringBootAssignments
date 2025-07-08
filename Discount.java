import java.util.List;

public class Discount {
    //Refactor the calculateTotalWithDiscount method with a private method for discount calculation
    public double calculateTotalWithDiscount(List<Double> prices, double discountPercentage) {  
        double total = 0.0;
        for (double price : prices) {
            total += price;
        }
        return applyDiscount(total, discountPercentage);
    }


    //Extract the discount calculation into a separate private method
    private double applyDiscount(double total, double discountPercentage) {
        return total - (total * (discountPercentage / 100));
    }
}
