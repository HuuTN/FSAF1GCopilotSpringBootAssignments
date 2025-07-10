import java.util.List;
import java.util.stream.Collectors;

public class Counter {
    public double calculateTotalWithDiscount(List<Integer> itemPrices, double discountPercentage) {
        double total = 0;
        for (int price : itemPrices) {
            total += price;
        }
        total = applyDiscount(total, discountPercentage);
        return total;
    }

    private double applyDiscount(double total, double discountPercentage) {
        return total - (total * (discountPercentage / 100));
    }

    public List<String> sortNames(List<String> names) {
        return names.stream()
                .sorted(String::compareToIgnoreCase)
                .collect(Collectors.toList());
    }
}