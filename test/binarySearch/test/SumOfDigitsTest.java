package binarySearch.test;

import binarySearch.SumOfDigits;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SumOfDigitsTest {

    // Test case for a positive integer
    @Test
    public void testSumOfDigitsPositive() {
        assertEquals(6, SumOfDigits.sumOfDigits(123));  // 1+2+3 = 6
        assertEquals(45, SumOfDigits.sumOfDigits(987654321)); // 9+8+7+6+5+4+3+2+1 = 45
    }

    // Test case for 0
    @Test
    public void testSumOfDigitsZero() {
        assertEquals(0, SumOfDigits.sumOfDigits(0));  // Edge case, should return 0
    }

    // Test case for a negative integer
    @Test
    public void testSumOfDigitsNegative() {
        assertEquals(15, SumOfDigits.sumOfDigits(-456));  // 4+5+6 = 15
    }

    // Test case for a very large number
    @Test
    public void testSumOfDigitsLargeNumber() {
        assertEquals(45, SumOfDigits.sumOfDigits(987654321));  // 9+8+7+6+5+4+3+2+1 = 45
    }
}
