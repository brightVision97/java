import java.util.Scanner;

/**
 * The crooked digit of a given number N is calculated using the number's
 * digits in a very weird and bendy algorithm.
 * <p>
 * The algorithm takes the following steps:
 * Sums the digits of the number N and stores the result back in N.
 * If the obtained result is bigger than 9, step 1. is repeated, otherwise the algorithm finishes.
 * The last obtained value of N is the result, calculated by the algorithm.
 */
public class CrookedDigits
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        
        String number = input.nextLine();
        
        int sum = 0;
        
        for (int i = 0; i < number.length(); i++)
        {
            char currentCh = number.charAt(i);
            
            if (Character.isDigit(currentCh))
                sum += currentCh - '0';
        }
        
        while (sum > 9)
        {
            int newSum = 0;
            
            while (sum > 0)
            {
                newSum += sum % 10;
                sum /= 10;
            }
            
            sum = newSum;
        }
        
        System.out.println(sum);
    }
}