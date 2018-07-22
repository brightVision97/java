package recursion;

import java.util.LinkedHashSet;

/**
 * Given n pairs of parentheses, write a function to
 * generate all combinations of well-formed parentheses.
 * <p>
 * For example, given n = 3, a solution set is:
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */
public class BalancedParentheses
{
    // recursive solution
    static void printParentheses(char[] result, int pos, int n, int open, int close)
    {
        if (close == n)
            printCombinations(result);
        else
        {
            if (open > close)
            {
                result[pos] = ')';
                printParentheses(result, pos + 1, n, open, close + 1);
            }
            
            if (open < n)
            {
                result[pos] = '(';
                printParentheses(result, pos + 1, n, open + 1, close);
            }
        }
    }
    
    // recursion + set solution
    static LinkedHashSet<String> generateExpression(String expression, int n)
    {
        LinkedHashSet<String> brackets = new LinkedHashSet<>();
        
        if (expression.length() == n)
        {
            brackets.add(expression);
            return brackets;
        }
        
        LinkedHashSet<String> currentBrackets =
                generateExpression(expression + "()", n);
        
        brackets.addAll(currentBrackets);
        currentBrackets = generateExpression("()" + expression, n);
        brackets.addAll(currentBrackets);
        currentBrackets = generateExpression("(" + expression + ")", n);
        brackets.addAll(currentBrackets);
        
        return brackets;
    }
    
    static void printCombinations(char[] result)
    {
        for (int i = 0; i < result.length; i++)
            System.out.print(result[i]);
        
        System.out.println();
    }
    
    public static void main(String[] args)
    {
        int n = 5;
        
        char[] result = new char[n * 2];
        
        printParentheses(result, 0, n, 0, 0);
    }
}