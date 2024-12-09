package filesearch;

public class Main {
    public static void main(String[] args) {
        // Input string for which we want to generate permutations
        String input = "abcde";

        // Create instances of the permutation classes
        StringPermutations permutations = new StringPermutations();
        StringPermutationsEnhanced permutationsEnhanced = new StringPermutationsEnhanced();

        // Measure the duration of the recursive permutation generation
        long startTime = System.nanoTime();
        permutations.generatePermutations(input);
        long durationRecursive = System.nanoTime() - startTime; // Calculate the elapsed time for the recursive method

        // Measure the duration of the non-recursive permutation generation
        startTime = System.nanoTime();
        permutationsEnhanced.generatePermutations(input); // Change the second parameter if you want to include duplicates
        long durationNonRecursive = System.nanoTime() - startTime; // Calculate the elapsed time for the non-recursive method

        // Print the durations for both approaches
        System.out.println("Recursive duration: " + durationRecursive + " ns");
        System.out.println("Non-recursive duration: " + durationNonRecursive + " ns");

        // Compare the durations and print which approach is faster
        System.out.println(durationRecursive > durationNonRecursive ? "Iterative Wins" : "Recursive Wins");
    }
}
