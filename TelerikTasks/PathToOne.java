import java.util.Scanner;

/**
 * You are given a number N.
 * <p>
 * You can perform 3 operations with N:
 * <p>
 * Didive it by 2
 * Only available if N is even
 * Increment by 1
 * Decrement by 1
 * <p>
 * You task is to calculate the minumum count of operations, the can make N equal to 1.
 */
public class PathToOne
{
    public static int shortestPath(int n)
    {
        int counter = 0;
        
        while (n > 1)
        {
            if (n % 2 == 0)
            {
                n /= 2;
                counter++;
            } else if (n % 4 == 1 || n == 3)
            {
                counter += 2;
                n = --n / 2;
            } else if (n % 4 == 3)
            {
                counter += 2;
                n = ++n / 2;
            }
        }
        
        return counter;
    }
    
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        
        int n = input.nextInt();
        
        if (input != null)
            input.close();
        
        System.out.println(shortestPath(n));
    }
}
