import java.util.Stack;

/**
 * You're now a baseball game point recorder.
 * <p>
 * Given a list of strings, each string can be one of the 4 following types:
 * <p>
 * Integer (one round's score): Directly represents the
 * number of points you get in this round.
 * "+" (one round's score): Represents that the points you get in
 * this round are the sum of the last two valid round's points.
 * "D" (one round's score): Represents that the points you get in
 * this round are the doubled data of the last valid round's points.
 * "C" (an operation, which isn't a round's score): Represents the
 * last valid round's points you get were invalid and should be removed.
 * Each round's operation is permanent and could have
 * an impact on the round before and the round after.
 * <p>
 * You need to return the sum of the points you could get in all the rounds.
 * <p>
 * Example:
 * <p>
 * Input: ["5","2","C","D","+"]
 * Output: 30
 * Explanation:
 * Round 1: You could get 5 points. The sum is: 5.
 * Round 2: You could get 2 points. The sum is: 7.
 * Operation 1: The round 2's data was invalid. The sum is: 5.
 * Round 3: You could get 10 points (the round 2's data has been removed). The sum is: 15.
 * Round 4: You could get 5 + 10 = 15 points. The sum is: 30.
 */
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