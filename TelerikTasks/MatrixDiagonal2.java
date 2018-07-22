import java.util.Scanner;

/**
 * You are given a square matrix of numbers, formed by powers of 2. See example
 * <p>
 * Example:
 * <p>
 * N = 4
 * <p>
 * 1  2  4  8
 * 2  4  8 16
 * 4  8 16 32
 * 8 16 32 64
 * <p>
 * The result is: 1 + 2 + 4 + 8 + 4 + 8 + 16 + 16 + 32 + 64 = 155
 * <p>
 * You task is to find the sum above the main diagonal.
 */
public class MatrixDiagonal2
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        
        int n = input.nextInt();
        
        long[][] matrix = new long[n][n];
        
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (i == 0)
                    matrix[i][j] = (long) Math.pow(2L, j);
                else
                    matrix[i][j] = (long) Math.pow(2L, j + i);
            }
        }
        
        long sum = 0;
        for (int i = 0; i < n; i++)
            for (int j = i; j < n; j++)
                sum += matrix[i][j];
        
        System.out.println(sum);
    }
}
