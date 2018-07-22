import java.util.Scanner;

/**
 * We know that you love math, so we have prepared a very interesting
 * task, that involves both geometry and prime numbers.
 * <p>
 * By a given N number, from which you need to generate a sequence of 1 to N inclusive.
 * For every prime number in that sequence, you need to print out all the other
 * numbers before it (and the number itself), whether they are prime or not
 * <p>
 * Example:
 * <p>
 * Let's say N=10
 * <p>
 * We have the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
 * The prime numbers are 1, 2, 3, 5, 7 - 5 prime numbers, so we prive 5 rows
 * Each row contains all the numbers for 1 to PRIME_NUMBER
 * <p>
 * Result:
 * 1
 * 1 2
 * 1 2 3
 * 1 2 3 4 5
 * 1 2 3 4 5 6 7
 * <p>
 * Lets make things simpler:
 * Print 0 if the numbers is not prime
 * Print 1 if the number is prime
 * <p>
 * Final result:
 * 1
 * 1 1
 * 1 1 1
 * 1 1 1 0 1
 * 1 1 1 0 1 0 1
 */
public class PrimeTriangle
{
    private static boolean isPrime(int n)
    {
        for (int i = 2; i < (int) Math.sqrt(n) + 1; i++)
            if (n % i == 0)
                return false;
        return true;
    }
    
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        
        int n = input.nextInt();
        
        for (int i = 1; i <= n; i++)
        {
            if (isPrime(i))
            {
                for (int j = 1; j <= i; j++)
                {
                    if (isPrime(j))
                        System.out.print(1);
                    else
                        System.out.print(0);
                }
                System.out.println();
            }
        }
    }
}