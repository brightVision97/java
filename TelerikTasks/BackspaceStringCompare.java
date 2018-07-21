import java.util.Stack;

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
        System.out.println(backspaceCompare("#ab#c", "#ad#c"));
    }
}
