package recursion;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Coki loves numbers. Yet, he cannot use them. Alas, he is that smart...
 * <p>
 * Now he wants to calculate expressions! He has digits and a number.
 * His task is to generate all valid mathematical expressions,
 * that can be can be done with the digits by inserting operators '+', '*' or '-',
 * between the digits, excluding at the begining and the end.
 * <p>
 * The expressions are calculated as the calculator does,
 * i.e. 2 + 3 * 5 = 5 * 5 = 25, not 2 + 15 = 17
 * <p>
 * Example:
 * <p>
 * From 123 the valid expressions are:
 * <p>
 * 1*2*3 = 6
 * 1*2+3 = 5
 * 1*2-3 = -1
 * 1*23  = 23
 * 1+2*3 = 9
 * 1+2+3 = 6
 * 1+2-3 = 0
 * 1+23  = 24
 * 1-2*3 = -3
 * 1-2+3 = 2
 * 1-2-3 = -4
 * 1-23  = -22
 * 12*3  = 36
 * 12+3  = 15
 * 12-3  = 9
 * 123   = 123
 * <p>
 * Help Coki to count the expressions, that evaluate to the provided number
 */
public class ExpressionsAgain
{
    static void fakeInput()
    {
        String input = "123\n" +
                "6";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    static int targetSum;
    
    public static int calculate(int[] digits, int currentSum)
    {
        if (digits.length == 0)
            return targetSum == currentSum ? 1 : 0;
        
        int count = 0;
        int number = 0;
        for (int i = 0; i < digits.length; i++)
        {
            number *= 10;
            number += digits[i];
            
            int[] numbers = new int[digits.length - 1];
            for (int j = i + 1; j < digits.length; j++)
                numbers[j - 1] = digits[j];
            
            count += calculate(numbers, currentSum + number);
            count += calculate(numbers, currentSum * number);
            count += calculate(numbers, currentSum - number);
            
            if (number == 0)
                break;
        }
        
        return count;
    }
    
    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);
        
        int[] numbers = Arrays.stream(input.nextLine().split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
        
        targetSum = input.nextInt();
        
        int number = 0;
        int result = 0;
        for (int i = 0; i < numbers.length; i++)
        {
            number *= 10;
            number += numbers[i];
            
            int[] newNumbers = new int[numbers.length - 1 - i];
            
            int index = 0;
            for (int j = i + 1; j < numbers.length; j++)
                newNumbers[index++] = numbers[j];
            
            result += calculate(newNumbers, number);
            
            if (number == 0)
                break;
        }
        
        System.out.println(result);
    }
}
