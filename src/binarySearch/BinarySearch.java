package binarySearch;

import java.util.ArrayList;
import java.util.Collections;

public class BinarySearch {

    // Recursive Binary Search Method
    public static int binarySearchRecursive(int[] arr, int left, int right, int target) {
        // Base case: If the search range is invalid
        if (left > right) {
            return -1;
        }
        
        int mid = left + (right - left) / 2;

        // Check if the target is at mid
        if (arr[mid] == target) {
            return mid;
        }

        // If the target is smaller, search in the left half
        if (arr[mid] > target) {
            return binarySearchRecursive(arr, left, mid - 1, target);
        }

        // Otherwise, search in the right half
        return binarySearchRecursive(arr, mid + 1, right, target);
    }

    // Method to handle null or empty arrays
    public static int binarySearch(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is null or empty");
        }
        return binarySearchRecursive(arr, 0, arr.length - 1, target);
    }

    public static ArrayList<Integer> findAllIndices(int[] arr, int target) {
        ArrayList<Integer> indices = new ArrayList<>();
        // Perform binary search to find the first occurrence
        int index = binarySearch(arr, target);
        
        if (index == -1) {
            return indices; // No target found
        }

        // Add the first found index
        indices.add(index);

        // Check left side for duplicates
        int left = index - 1;
        while (left >= 0 && arr[left] == target) {
            indices.add(left);
            left--;
        }

        // Check right side for duplicates
        int right = index + 1;
        while (right < arr.length && arr[right] == target) {
            indices.add(right);
            right++;
        }

        // Sort the indices to maintain ascending order
        Collections.sort(indices);

        return indices;
    }

    // Method to handle strings in binary search
    public static int binarySearchRecursive(String[] arr, int left, int right, String target) {
        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;

        if (arr[mid].equals(target)) {
            return mid;
        }

        if (arr[mid].compareTo(target) > 0) {
            return binarySearchRecursive(arr, left, mid - 1, target);
        }

        return binarySearchRecursive(arr, mid + 1, right, target);
    }

    public static int binarySearch(String[] arr, String target) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is null or empty");
        }
        return binarySearchRecursive(arr, 0, arr.length - 1, target);
    }
}
