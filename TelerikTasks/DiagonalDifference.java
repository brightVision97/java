import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Given a square matrix, calculate the absolute difference between the sums of its diagonals.
 */
public class DiagonalDifference
{
    static void fakeInput()
    {
        String input = "3\n" +
                "11 2 4\n" +
                "4 5 6\n" +
                "10 8 -12";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    static int calculateDiff(int[][] matrix)
    {
        int diagMain = 0;
        int diagSecond = 0;
        for (int i = 0; i < matrix.length; i++)
        {
            for (int j = 0; j < matrix[i].length; j++)
            {
                if (i - j == 0)
                    diagMain += matrix[i][j];
                
                if (i + j == matrix.length - 1)
                    diagSecond += matrix[i][j];
            }
        }
        
        return Math.abs(diagMain - diagSecond);
    }
    
    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);
        
        int n = input.nextInt();
        input.nextLine();
        
        int[][] matrix = new int[n][n];
        
        for (int i = 0; i < n; i++)
        {
            int[] row = Arrays.stream(input.nextLine().split("\\s"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            
            matrix[i] = row;
        }
        
        System.out.println(calculateDiff(matrix));
    }
}