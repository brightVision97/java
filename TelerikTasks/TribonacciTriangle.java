import java.util.Scanner;

/**
 * You all know the Fibonacci sequence. Well, the Tribonacci
 * sequence is almost the same, but it uses the last three numbers
 * (instead of the last two) to calculate the next number in the sequence.
 * So, we can define each element in the sequence as:
 * <p>
 * T n = T n-1 + T n-2 + T n-3 where T n is the current Tribonacci number
 * (n is the index of the current Tribonacci number).
 * <p>
 * The Tribonacci sequence can begin with any three integer numbers –
 * positive or negative – and continue as described by the formula above.
 * <p>
 * Now, a Tribonacci triangle is a triangle of numbers from the Tribonacci sequence.
 * The first line of the triangle contains only the first number of the Tribonacci sequence.
 * The second line contains the second and third numbers of the Tribonacci sequence,
 * separated by a single whitespace (" "). The third line contains the
 * next three numbers of the Tribonacci sequence (again, separated by whitespaces).
 * The fourth line contains the next four numbers and so on. Basically,
 * every line contains one more number than the previous.
 * <p>
 * Your task is to write a program, which prints to the console a
 * Tribonacci triangle by given the first three numbers of the Tribonacci
 * sequence, and the number of lines in the triangle.
 */
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