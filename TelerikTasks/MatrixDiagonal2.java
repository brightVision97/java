import java.util.Scanner;

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
