import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class PrimesToN
{
    static void fakeInput()
    {
        String input = "2";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    static boolean isPrime(int number)
    {
        for (int i = 2; i <= Math.sqrt(number); i++)
            if (number % i == 0)
                return false;

        return true;
    }

    static void printPrimeNumbersTo(int number)
    {
        for (int i = 1; i <= number; i++)
            if (isPrime(i))
                System.out.print(i + " ");
    }

    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);

        printPrimeNumbersTo(input.nextInt());
    }
}
