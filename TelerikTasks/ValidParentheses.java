import java.util.Stack;

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