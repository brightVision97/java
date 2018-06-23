package recursion;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ValidateBrackets
{
    static void fakeInput()
    {
        String input = "9\n" +
                "()()()()()\n" +
                ")(\n" +
                "(((**\n" +
                "*\n" +
                "\n" +
                "***\n" +
                "**\n" +
                "(**)\n" +
                "****))";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    static boolean validate(String expression, int count)
    {
        for (int i = 0; i < expression.length(); i++)
        {
            if (expression.charAt(i) == '(')
                count++;
            
            if (expression.charAt(i) == ')')
                count--;
            
            if (expression.charAt(i) == '*')
            {
                String newExpression1 = expression.replaceFirst("\\*", "(");
                String newExpression2 = expression.replaceFirst("\\*", ")");
                String newExpression3 = expression.replaceFirst("\\*", "");
                
                if (validate(newExpression1, 0) || validate(newExpression2, 0) ||
                        validate(newExpression3, 0))
                    return true;
                
            }
            
            if (count < 0)
                return false;
        }
        
        if (count == 0)
            return true;
        
        return false;
    }
    
    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);
        
        int n = input.nextInt();
        input.nextLine();
        
        List<String> expressions = new ArrayList<>();
        
        for (int i = 0; i < n; i++)
            expressions.add(input.nextLine());
        
        for (int i = 0; i < expressions.size(); i++)
        {
            if (validate(expressions.get(i), 0))
                System.out.println("yes");
            else
                System.out.println("no");
        }
    }
}
