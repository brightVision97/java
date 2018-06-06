import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class HorsePath
{
    static void fakeInput()
    {
        String input = "5";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    static void horseJump(int[][] matrix)
    {
        int[] rowsDir = {-2, -2, -1, -1, +1, +1, +2, +2};
        int[] colsDir = {-1, +1, -2, +2, -2, +2, -1, +1};

        int counter = 1;

        for (int row = 0; row < matrix.length; row++)
        {
            for (int col = 0; col < matrix[row].length; col++)
            {
                int currentRow = row;
                int currentCol = col;

                while (matrix[currentRow][currentCol] == 0)
                {
                    matrix[currentRow][currentCol] = counter++;

                    for (int dir = 0; dir < rowsDir.length; dir++)
                    {
                        int nextRow = currentRow + rowsDir[dir];
                        int nextCol = currentCol + colsDir[dir];

                        if (nextRow < 0 || nextRow >= matrix.length ||
                                nextCol < 0 || nextCol >= matrix[row].length)
                            continue;

                        if (matrix[nextRow][nextCol] != 0)
                            continue;

                        currentRow = nextRow;
                        currentCol = nextCol;
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        int[][] matrix = new int[n][n];

        horseJump(matrix);

        for (int row = 0; row < matrix.length; row++)
        {
            for (int col = 0; col < matrix[row].length; col++)
                System.out.print(matrix[row][col] + " ");
            System.out.println();
        }
    }
}
