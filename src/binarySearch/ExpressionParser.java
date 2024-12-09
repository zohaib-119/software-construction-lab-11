package binarySearch;

import java.util.*;

public class ExpressionParser {

    // Tracks the current position in the expression string
    private static int currentIndex = 0; 

    /**
     * Evaluates the entire mathematical expression.
     * 
     * @param expression A string representing the mathematical expression to evaluate.
     * @return The result of the evaluated expression as a double.
     * @throws IllegalArgumentException if the expression is null, empty, or improperly formatted.
     */
    public static double evaluateExpression(String expression) throws IllegalArgumentException {
        // Check if the expression is null or empty
        if (expression == null || expression.isEmpty()) {
            throw new IllegalArgumentException("Expression cannot be null or empty.");
        }
        // Reset the current index before evaluation
        currentIndex = 0;
        // Remove all whitespaces from the expression for easier parsing
        expression = expression.replaceAll("\\s+", ""); 
        // Start parsing from the addition/subtraction level
        return parseAdditionSubtraction(expression);
    }

    /**
     * Parses addition and subtraction operators (+, -) in the expression.
     * 
     * @param expression The expression string to parse.
     * @return The result of the addition and subtraction operations as a double.
     */
    private static double parseAdditionSubtraction(String expression) {
        // Start by parsing multiplication and division operations first (due to operator precedence)
        double result = parseMultiplicationDivision(expression);
        
        // Continue parsing the expression for addition and subtraction operations
        while (currentIndex < expression.length()) {
            char operator = expression.charAt(currentIndex);
            // Check if the current character is a + or - operator
            if (operator == '+' || operator == '-') {
                // Move past the operator
                currentIndex++; 
                // Parse the next operand
                double rightOperand = parseMultiplicationDivision(expression);
                // Perform the appropriate operation based on the operator
                if (operator == '+') {
                    result += rightOperand;
                } else {
                    result -= rightOperand;
                }
            } else {
                break; // Exit if no more addition or subtraction operators
            }
        }
        return result;
    }

    /**
     * Parses multiplication and division operators (*, /) in the expression.
     * 
     * @param expression The expression string to parse.
     * @return The result of the multiplication and division operations as a double.
     */
    private static double parseMultiplicationDivision(String expression) {
        // Start by parsing parentheses (if any), which may contain sub-expressions
        double result = parseParentheses(expression);
        
        // Continue parsing the expression for multiplication and division operations
        while (currentIndex < expression.length()) {
            char operator = expression.charAt(currentIndex);
            // Check if the current character is a * or / operator
            if (operator == '*' || operator == '/') {
                // Move past the operator
                currentIndex++; 
                // Parse the next operand
                double rightOperand = parseParentheses(expression);
                // Perform the appropriate operation based on the operator
                if (operator == '*') {
                    result *= rightOperand;
                } else {
                    result /= rightOperand;
                }
            } else {
                break; // Exit if no more multiplication or division operators
            }
        }
        return result;
    }

    /**
     * Handles parsing of numbers and sub-expressions inside parentheses.
     * 
     * @param expression The expression string to parse.
     * @return The evaluated result of the current sub-expression or number as a double.
     * @throws IllegalArgumentException if parentheses are mismatched.
     */
    private static double parseParentheses(String expression) {
        // If the current index is out of bounds, raise an error
        if (currentIndex >= expression.length()) {
            throw new IllegalArgumentException("Unexpected end of expression.");
        }

        // Get the current character
        char currentChar = expression.charAt(currentIndex);

        // If the current character is an opening parenthesis, parse the enclosed expression
        if (currentChar == '(') {
            currentIndex++; // Move past '('
            // Parse the expression inside the parentheses
            double result = parseAdditionSubtraction(expression);
            // Check if the closing parenthesis is present at the correct position
            if (currentIndex >= expression.length() || expression.charAt(currentIndex) != ')') {
                throw new IllegalArgumentException("Mismatched parentheses.");
            }
            currentIndex++; // Move past ')'
            return result;
        }

        // Otherwise, parse the current number (integer or floating-point)
        return parseNumber(expression);
    }

    /**
     * Parses a number (integer or floating-point) from the expression.
     * 
     * @param expression The expression string to parse.
     * @return The parsed number as a double.
     * @throws IllegalArgumentException if the number format is invalid.
     */
    private static double parseNumber(String expression) {
        StringBuilder sb = new StringBuilder();
        boolean hasDecimal = false;
        
        // Continue parsing the string to build the number
        while (currentIndex < expression.length()) {
            char currentChar = expression.charAt(currentIndex);
            // If the character is a digit, add it to the number being built
            if (Character.isDigit(currentChar)) {
                sb.append(currentChar);
            } 
            // If the character is a decimal point and it's the first one, add it
            else if (currentChar == '.' && !hasDecimal) {
                sb.append(currentChar);
                hasDecimal = true;
            } else {
                break; // Exit the loop when a non-number character is encountered
            }
            currentIndex++;
        }

        // If no number was parsed, throw an exception
        if (sb.length() == 0) {
            throw new IllegalArgumentException("Invalid number format.");
        }

        // Convert the parsed number string to a double and return it
        return Double.parseDouble(sb.toString());
    }

}
