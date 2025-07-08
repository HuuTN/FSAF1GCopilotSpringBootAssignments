public class Fibonacci {

    // Returns the nth Fibonacci number
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // Example usage
    public static void main(String[] args) {
        int n = 10; // Change this value to compute a different Fibonacci number
        System.out.println("Fibonacci number at position " + n + " is " + fibonacci(n));
    }
}