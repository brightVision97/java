import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * The whole name of Coki is Kociokkociiev (How do you even pronounce it, right?)
 * <p>
 * So, you task is to find if string contains the letters of another string,
 * in the same order, but not continiously.
 * <p>
 * Example:
 * <p>
 * Input:
 * coki
 * kociokkociiev
 * Output:
 * true
 */
public class Subsequence
{
    static boolean isSubsequence(String substr, String str)
    {
        Stack<Character> stack = new Stack<>();
        
        StringBuilder sb = new StringBuilder(substr);
        
        for (char ch : str.toCharArray())
        {
            if (ch == sb.charAt(0))
            {
                stack.push(ch);
                sb.deleteCharAt(0);
            }
            
            if (stack.size() == substr.length())
                return true;
        }
        
        return false;
    }
    
    public static void main(String[] args) throws IOException
    {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        
        String substr = input.readLine();
        String str = input.readLine();
        
        System.out.println(isSubsequence(substr, str));
    }
}