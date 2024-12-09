package filesearch;

import java.util.ArrayList;
import java.util.List;

public class StringPermutations {

    /**
     * Generates all permutations of the given string.
     *
     * @param str The input string for which permutations are to be generated.
     * @return A list of all permutations of the input string.
     * @throws IllegalArgumentException If the input string is null or empty.
     */
    public List<String> generatePermutations(String str) {
        // Handle null or empty input
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty.");
        }

        List<String> permutations = new ArrayList<>();
        generatePermutationsRecursive("", str, permutations);
        return permutations;
    }

    /**
     * Recursive helper method to generate permutations.
     *
     * @param prefix The current prefix of the permutation being built.
     * @param remaining The remaining characters to permute.
     * @param result The list to store all generated permutations.
     */
    private void generatePermutationsRecursive(String prefix, String remaining, List<String> result) {
        // Base case: if no characters are left to permute, add the permutation to the result
        if (remaining.length() == 0) {
            result.add(prefix);
            return;
        }

        // Loop through the remaining characters
        for (int i = 0; i < remaining.length(); i++) {
            // Choose the current character
            char currentChar = remaining.charAt(i);
            // Form the new remaining string by excluding the current character
            String newRemaining = remaining.substring(0, i) + remaining.substring(i + 1);
            // Recur with the new prefix and remaining characters
            generatePermutationsRecursive(prefix + currentChar, newRemaining, result);
        }
    }
}
