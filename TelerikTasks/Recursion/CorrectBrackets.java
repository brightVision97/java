package recursion;

import java.util.*;

public class CorrectBrackets
{
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

    public static void main(String[] args)
    {
        LinkedHashSet<String> brackets = generateExpression("", 10);

        Iterator<String> it = brackets.iterator();
        while (it.hasNext())
            System.out.println(it.next());
    }
}