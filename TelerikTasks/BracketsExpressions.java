import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * You are given a valid mathematical expression.
 * <p>
 * Extract the valid expressions, between brackets.
 * <p>
 * Example:
 * <p>
 * Input: 5 * (123 * (1 + 3) + ((4 - 3) / 6))
 * Output:
 * (1 + 3)
 * (4 - 3)
 * ((4 - 3) / 6)
 * (123 * (1 + 3) + ((4 - 3) / 6))
 */
public class BracketsExpressions
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String expression = br.readLine().trim();
        
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < expression.length(); i++)
        {
            if (expression.charAt(i) == '(')
                stack.push(i);
            if (expression.charAt(i) == ')')
                System.out.println(expression.substring(stack.pop(), i + 1));
        }
    }
}