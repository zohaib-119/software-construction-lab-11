package filesearch.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import filesearch.StringPermutationsEnhanced;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StringPermutationsEnhancedTest {

    private StringPermutationsEnhanced stringPermutations;

    @BeforeEach
    public void setUp() {
        stringPermutations = new StringPermutationsEnhanced();
    }

    @Test
    public void testGeneratePermutationsWithNormalString() {
        String input = "abc";
        List<String> permutations = stringPermutations.generatePermutations(input);
        
        // Verify that the correct number of permutations are generated (3! = 6)
        assertEquals(6, permutations.size());
        assertTrue(permutations.contains("abc"));
        assertTrue(permutations.contains("acb"));
        assertTrue(permutations.contains("bac"));
        assertTrue(permutations.contains("bca"));
        assertTrue(permutations.contains("cab"));
        assertTrue(permutations.contains("cba"));
    }

    @Test
    public void testGeneratePermutationsWithSingleCharacter() {
        String input = "a";
        List<String> permutations = stringPermutations.generatePermutations(input);
        
        // Only one permutation is possible
        assertEquals(1, permutations.size());
        assertTrue(permutations.contains("a"));
    }

    @Test
    public void testGeneratePermutationsWithRepeatedCharacters() {
        String input = "aab";
        List<String> permutations = stringPermutations.generatePermutations(input);
        
        // Verify that the correct number of unique permutations are generated (3! / 2! = 3)
        assertEquals(6, permutations.size());
        assertTrue(permutations.contains("aab"));
        assertTrue(permutations.contains("aba"));
        assertTrue(permutations.contains("baa"));
    }
    
    @Test
    public void testGeneratePermutationsWithLongString() {
        String input = "abcd3"; // You can change this to test longer strings.
        List<String> permutations = stringPermutations.generatePermutations(input);
        
        // Verify the number of permutations (4! = 24)
        assertEquals(120, permutations.size());
    }
    
    
    @Test
    public void testGeneratePermutationsWithEmptyString() {
        String input = "";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            stringPermutations.generatePermutations(input);
        });
        
        assertEquals("Input string cannot be null or empty.", exception.getMessage());
    }

    @Test
    public void testGeneratePermutationsWithNullString() {
        String input = null;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            stringPermutations.generatePermutations(input);
        });
        
        assertEquals("Input string cannot be null or empty.", exception.getMessage());
    }
}
