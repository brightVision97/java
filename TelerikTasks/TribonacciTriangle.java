import java.util.Scanner;

public class TribonacciTriangle
{
    private static long tribonacci(long n, long n1, long n2)
    {
            return n + n1 + n2;
    }

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        long[] inputInts = new long[4];

        for (int i = 0; i < inputInts.length; i++)
            inputInts[i] = input.nextInt();

        long nextN = tribonacci(inputInts[0], inputInts[1], inputInts[2]);

        for (int i = 1; i <= inputInts[inputInts.length - 1]; i++)
        {
            if (i == 1)
                System.out.println(inputInts[0]);
            else if (i == 2)
                System.out.println(inputInts[1] + " " + inputInts[2]);
            else
            {

                for (int j = 0; j < i; j++)
                {
                    System.out.print(nextN + " ");
                    inputInts[0] = inputInts[1];
                    inputInts[1] = inputInts[2];
                    inputInts[2] = nextN;
                    nextN = tribonacci(inputInts[0], inputInts[1], inputInts[2]);
                }

                System.out.println();
            }
        }
    }
}