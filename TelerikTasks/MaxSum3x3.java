import java.io.ByteArrayInputStream;
import java.util.Scanner;

/**
 * Write a program that reads a rectangular matrix of size N x M
 * and finds in it the square 3 x 3 that has maximal sum of its elements
 * <p>
 * Print that sum.
 */
public class MaxSum3x3
{
    static void fakeInput()
    {
        String input = "5 5" +
                "\n1 1 3 3 5\n" +
                "-6 -7 2 -3 -1\n" +
                "3 0 -4 5 9\n" +
                "7 -7 0 1 0\n" +
                "-7 -6 -4 -4 9\t";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    public static void main(String[] args)
    {
        fakeInput();
        Scanner reader = new Scanner(System.in);
        
        int n = reader.nextInt();
        int m = reader.nextInt();
        
        int[][] matrix = new int[n][m];
        
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                matrix[i][j] = reader.nextInt();
        
        int bestSum = Integer.MIN_VALUE;
        for (int row = 0; row < matrix.length - 2; row++)
        {
            for (int col = 0; col < matrix[row].length - 2; col++)
            {
                int currentSum = matrix[row][col] + matrix[row][col + 1] + matrix[row][col + 2] +
                        matrix[row + 1][col] + matrix[row + 1][col + 1] + matrix[row + 1][col + 2] +
                        matrix[row + 2][col] + matrix[row + 2][col + 1] + matrix[row + 2][col + 2];
                
                if (currentSum > bestSum)
                    bestSum = currentSum;
            }
        }
        
        System.out.println(bestSum);
    }
}