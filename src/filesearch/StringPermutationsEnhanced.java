package filesearch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringPermutationsEnhanced {

    /**
     * Generates all permutations of the given string using a non-recursive approach.
     *
     * @param str The input string to generate permutations for.
     * @return A list of all permutations of the input string.
     */
    public List<String> generatePermutations(String str) {
    	
    	 // Handle null or empty input
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty.");
        }
    	
        List<String> result = new ArrayList<>();
        List<String> currentPermutations = new ArrayList<>();
        currentPermutations.add("");

        for (char c : str.toCharArray()) {
            List<String> newPermutations = new ArrayList<>();
            for (String perm : currentPermutations) {
                for (int j = 0; j <= perm.length(); j++) {
                    newPermutations.add(perm.substring(0, j) + c + perm.substring(j));
                }
            }
            currentPermutations = newPermutations;
        }
        return currentPermutations;
    }
}
