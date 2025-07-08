// Function to compare two numbers
// Returns 1 if a > b, -1 if a < b, 0 if equal
function compareNumbers(a, b) {
    if (a > b) {
        return 1;
    } else if (a < b) {
        return -1;
    } else {
        return 0;
    }
}

// Example usage:
console.log(compareNumbers(5, 3)); // Output: 1
console.log(compareNumbers(2, 7)); // Output: -1
console.log(compareNumbers(4, 4)); // Output: 0
