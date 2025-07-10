// Create calculateTotalWithDiscount method use applyDiscount method
public double calculateTotalWithDiscount(List<Integer> itemPrices, double discountPercentage) {
    double total = 0;
    for (int price : itemPrices) {
        total += price;
    }
    // Use the applyDiscount method to calculate the total after discount
    return applyDiscount(total, discountPercentage);
}


// Extract the discount calculation into a separate private method
private double applyDiscount(double total, double discountPercentage) {
    return total - (total * (discountPercentage / 100));
}