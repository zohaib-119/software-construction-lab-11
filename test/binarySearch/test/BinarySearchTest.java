package binarySearch.test;

import binarySearch.BinarySearch;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class BinarySearchTest {

    // Test case for binarySearch with integer array
    @Test
    public void testBinarySearchInt() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertEquals(4, BinarySearch.binarySearch(arr, 5)); // Should return index 4
        assertEquals(-1, BinarySearch.binarySearch(arr, 10)); // Should return -1 (not found)
    }

    // Test case for binarySearch with string array
    @Test
    public void testBinarySearchString() {
        String[] arr = {"apple", "banana", "cherry", "date", "elderberry"};
        assertEquals(2, BinarySearch.binarySearch(arr, "cherry")); // Should return index 2
        assertEquals(-1, BinarySearch.binarySearch(arr, "fig")); // Should return -1 (not found)
    }

    // Test case for findAllIndices when target appears multiple times
    @Test
    public void testFindAllIndices() {
        int[] arr = {1, 2, 3, 5, 5, 5, 7, 8};
        ArrayList<Integer> indices = BinarySearch.findAllIndices(arr, 5);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(3);
        expected.add(4);
        expected.add(5);
        assertEquals(expected, indices); // Should return all indices where 5 appears
    }

    // Test case for findAllIndices when target is not in the array
    @Test
    public void testFindAllIndicesNotFound() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayList<Integer> indices = BinarySearch.findAllIndices(arr, 8);
        ArrayList<Integer> expected = new ArrayList<>();
        assertEquals(expected, indices); // Should return an empty list as 8 is not found
    }

    // Test case for an empty array in binarySearch
    @Test
    public void testEmptyArrayBinarySearch() {
        int[] arr = {};
        assertThrows(IllegalArgumentException.class, () -> {
            BinarySearch.binarySearch(arr, 5); // Should throw an IllegalArgumentException
        });
    }

    // Test case for a null array in binarySearch
    @Test
    public void testNullArrayBinarySearch() {
        int[] arr = null;
        assertThrows(IllegalArgumentException.class, () -> {
            BinarySearch.binarySearch(arr, 5); // Should throw an IllegalArgumentException
        });
    }
}
