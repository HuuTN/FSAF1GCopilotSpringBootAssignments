//1 Context-Aware Code Completion
public String getGreeting(String name) {
    return "Hello, " + name + "!";
}
//2
// Code Snippet for Context-Aware Code Completion

//3 Comment-Driven Development (Natural Language to Code)
//create a function that finds the maximum value in an array
public class AI_pair_programing_concept {
    public static void main(String[] args) {
        int[] numbers = {3, 5, 7, 2, 8, -1, 4};
        int maxValue = findMaxValue(numbers);
        System.out.println("The maximum value in the array is: " + maxValue);
    }

    public static int findMaxValue(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }
        
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
}

// 
//The findMaxValue function takes an array of integers as input and returns the largest value found in that array.

// Step-by-step explanation:

// Input validation:
// It first checks if the array is null or empty. If so, it throws an IllegalArgumentException to prevent errors later in the code.

// Initialization:
// It sets a variable max to the first element of the array. This will keep track of the largest value found so far.

// Iteration:
// It loops through the rest of the array (starting from the second element). For each element, it checks if that element is greater than the current max. If it is, it updates max to this new value.

// Result:
// After checking all elements, it returns the largest value found.

// Example:
// Given the array {3, 5, 7, 2, 8, -1, 4}, the function will return 8, since that's the largest number in the array.

// Gotcha:
// If you pass an empty array or null, the function will throw an exception instead of returning a value. This is a good practice to avoid unexpected behavior.
