import java.util.*;

public class BracketsExpressions
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        String expression = input.nextLine().trim();

        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new TreeMap<>();

        for (int i = 0; i < expression.length(); i++)
        {
            char currentChar = expression.charAt(i);

            if (currentChar == '(')
                stack.push(i);
            else if (currentChar == ')')
                map.put(i, stack.pop());
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet())
        {
            for (int i = entry.getValue(); i <= entry.getKey(); i++)
                System.out.print(expression.charAt(i));
            System.out.println();
        }
    }
}