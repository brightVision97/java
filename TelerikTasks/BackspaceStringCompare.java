import java.util.Stack;

/**
 * Given two strings S and T, return if they are equal when both are
 * typed into empty text editors. # means a backspace character.
 * <p>
 * Example:
 * <p>
 * Input: S = "ab#c", T = "ad#c"
 * Output: true
 * Explanation: Both S and T become "ac".
 */
public class BackspaceStringCompare
{
    public static String removeBackspaces(String str)
    {
        Stack<Character> stack = new Stack<>();
        
        for (char ch : str.toCharArray())
        {
            if (ch == '#')
            {
                if (stack.empty())
                    continue;
                stack.pop();
            } else
                stack.push(ch);
        }
        
        StringBuilder sb = new StringBuilder();
        
        while (!stack.empty())
            sb.append(stack.pop());
        
        return sb.reverse().toString();
    }
    
    public static boolean backspaceCompare(String S, String T)
    {
        return removeBackspaces(S).equals(removeBackspaces(T));
    }
    
    public static void main(String[] args)
    {
        System.out.println(backspaceCompare("ab#c", "ad#c"));
    }
}
