import java.io.ByteArrayInputStream;
import java.util.Scanner;

/**
 * Write a program that reads from the console a positive integer N
 * and prints all the numbers from 1 to N not divisible by 3 or 7,
 * on a single line, separated by a space.
 */
public class NotDivisableNumber
{
    static void fakeInput()
    {
        String input = "10";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);
        
        int number = input.nextInt();
        
        for (int i = 1; i <= number; i++)
        {
            if (i % 3 == 0 || i % 7 == 0)
                continue;
            else
                System.out.print(i + " ");
        }
    }
}
