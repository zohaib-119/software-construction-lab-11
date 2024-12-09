package binarySearch.test;

import binarySearch.ExpressionParser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExpressionParserTest {

    @Test
    public void testSimpleExpression() {
        String expression = "3 + 5 * 2";
        assertEquals(13.0, ExpressionParser.evaluateExpression(expression));
    }

    @Test
    public void testParentheses() {
        String expression = "(3 + 5) * 2";
        assertEquals(16.0, ExpressionParser.evaluateExpression(expression));
    }

    @Test
    public void testFloatingPointNumbers() {
        String expression = "3.5 + 2.5 * 2";
        assertEquals(8.5, ExpressionParser.evaluateExpression(expression));
    }

    @Test
    public void testDivision() {
        String expression = "10 / 2 + 3";
        assertEquals(8.0, ExpressionParser.evaluateExpression(expression));
    }


    @Test
    public void testInvalidExpression() {
        String expression = "3 + * 5";
        assertThrows(IllegalArgumentException.class, () -> {
            ExpressionParser.evaluateExpression(expression);
        });
    }

    @Test
    public void testEmptyExpression() {
        assertThrows(IllegalArgumentException.class, () -> {
            ExpressionParser.evaluateExpression("");
        });
    }

    @Test
    public void testInvalidParentheses() {
        String expression = "3 + (5 * 2";
        assertThrows(IllegalArgumentException.class, () -> {
            ExpressionParser.evaluateExpression(expression);
        });
    }

}
