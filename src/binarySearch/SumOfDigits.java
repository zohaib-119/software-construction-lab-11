package binarySearch;

public class SumOfDigits {
    
    // Recursive function to calculate the sum of digits
    public static int sumOfDigits(int number) {
        // Convert to positive if the number is negative
        number = Math.abs(number);
        
        // Base case: if number becomes 0, return 0
        if (number == 0) {
            return 0;
        }
        
        // Recursive case: sum the last digit and recursively process the rest
        return number % 10 + sumOfDigits(number / 10);
    }
}
