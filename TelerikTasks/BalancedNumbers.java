import java.io.ByteArrayInputStream;
import java.util.Scanner;

/**
 * A balanced number is a 3-digit number whose second
 * digit is equal to the sum of the first and last digit.
 * <p>
 * Write a program which reads and sums balanced numbers.
 * You should stop when an unbalanced number is given.
 */
public class BalancedNumbers
{
    private static void fakeInput()
    {
        String input = "275\n" +
                "693\n" +
                "110\n" +
                "742";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);
        
        int sum = 0;
        while (true)
        {
            String num = input.nextLine();
            
            int digit1 = num.charAt(0) - '0';
            int digit2 = num.charAt(1) - '0';
            int digit3 = num.charAt(2) - '0';
            
            if (digit1 + digit3 == digit2)
            {
                sum += Integer.parseInt(num);
                continue;
            } else
                break;
        }
        
        System.out.println(sum);
    }
}