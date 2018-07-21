import java.util.Stack;

public class BaseballGame
{
    public static int calPoints(String[] ops)
    {
        Stack<Integer> stack = new Stack<>();
        
        int ans = 0;
        
        for (String str : ops)
        {
            switch (str)
            {
                case "+":
                {
                    int top = stack.pop();
                    int newTop = top + stack.peek();
                    stack.push(top);
                    stack.push(newTop);
                    break;
                }
                case "C":
                    stack.pop();
                    break;
                case "D":
                {
                    int top = stack.pop();
                    int newTop = top * 2;
                    stack.push(top);
                    stack.push(newTop);
                    break;
                }
                default:
                    stack.push(Integer.valueOf(str));
                    break;
            }
        }
        
        while (!stack.empty())
            ans += stack.pop();
        
        return ans;
    }
    
    public static void main(String[] args)
    {
        System.out.println(calPoints(new String[]{"5", "2", "C", "D", "+"}));
    }
}