import java.util.Scanner;

/**
 * Write a program that finds and prints the biggest prime number which is <= N.
 */
public class BiggestPrimeNumber
{
    static boolean isPrime(int n)
    {
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;
        
        return true;
    }
    
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        
        int n = input.nextInt();
        
        while (!isPrime(n))
            --n;
        
        System.out.println(n);
    }
}
