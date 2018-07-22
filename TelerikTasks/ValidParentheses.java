import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 * <p>
 * Example:
 * <p>
 * Input: "()"
 * Output: true
 */
public class ValidParentheses
{
    public static boolean isValid(String s)
    {
        Stack<Character> stack = new Stack<>();
        
        for (char ch : s.toCharArray())
        {
            if (stack.size() > 0)
            {
                if ((ch == ')' && stack.peek() == '(') || (ch == ']' && stack.peek() == '[') ||
                        (ch == '}' && stack.peek() == '{'))
                    stack.pop();
            } else
                stack.push(ch);
        }
        
        return stack.empty();
    }
    
    public static void main(String[] args)
    {
        System.out.println(isValid("{}()()"));
    }
}