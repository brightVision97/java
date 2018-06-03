import java.util.Scanner;

public class PrimeTriangle
{
    private static boolean isPrime(int n)
    {
        for (int i = 2; i < (int)Math.sqrt(n) + 1; i++)
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