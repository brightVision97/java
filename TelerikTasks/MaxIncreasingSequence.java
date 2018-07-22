import java.io.ByteArrayInputStream;
import java.util.Scanner;

/**
 * Write a program that finds the length of the maximal
 * increasing sequence in an array of N integers.
 */
public class MaxIncreasingSequence
{
    static void fakeInput()
    {
        String input = "8\n" +
                "7\n" +
                "3\n" +
                "2\n" +
                "3\n" +
                "5\n" +
                "2\n" +
                "2\n" +
                "4";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);
        
        int n = input.nextInt();
        
        int[] numbers = new int[n];
        
        for (int i = 0; i < n; i++)
            numbers[i] = input.nextInt();
        
        int maxIncrSeq = 1;
        int currMaxIncSeq = 1;
        
        for (int i = 1; i < n; i++)
        {
            if (numbers[i] > numbers[i - 1])
                currMaxIncSeq++;
            else
            {
                if (currMaxIncSeq > maxIncrSeq)
                    maxIncrSeq = currMaxIncSeq;
                
                currMaxIncSeq = 1;
            }
        }
        
        System.out.println(maxIncrSeq);
    }
}
