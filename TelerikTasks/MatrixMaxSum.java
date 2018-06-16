import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class MatrixMaxSum
{
    static void fakeInput()
    {
        String input = "5\n" +
                "1 22 3 41 5 2\n" +
                "2 13 4 -5 6 5\n" +
                "-6 5 9 31 2 8\n" +
                "3 14 5 -6 7 4\n" +
                "4 -5 6 -7 8 7\n" +
                "-3 -3 3 3 4 -3 -4 3";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);

        int rows = input.nextInt();
        input.nextLine();

        int[] firstRow = Arrays.stream(input.nextLine().split("\\s"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] matrix = new int[rows][firstRow.length];

        matrix[0] = firstRow;

        for (int i = 1; i < rows; i++)
            matrix[i] = Arrays.stream(input.nextLine().split("\\s"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

        int[] coords = Arrays.stream(input.nextLine().split("\\s"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < coords.length; i += 2)
        {
            int currentSum = 0;
            int currentRow = coords[i];
            int currentColumn = coords[i + 1];

            int currentColumnRow = Math.abs(currentColumn);
            int currentRowColumn = Math.abs(currentRow);
            
            if (currentRow >= 0)
                for (int j = 0; j < currentColumnRow; j++)
                    currentSum += matrix[currentRow - 1][j];
            else
                for (int j = currentColumnRow - 1; j < firstRow.length; j++)
                    currentSum += matrix[currentRowColumn - 1][j];

            if (currentColumn >= 0)
                for (int j = currentRowColumn - 1; j >= 0; j--)
                    currentSum += matrix[j][currentColumn - 1];
            else
                for(int j = currentRowColumn - 1; j < rows; j++)
                    currentSum += matrix[j][currentColumnRow - 1];

            if (currentSum - matrix[currentRowColumn - 1][currentColumnRow - 1] > maxSum)
                maxSum  = currentSum - matrix[currentRowColumn - 1][currentColumnRow - 1];
        }

        System.out.println(maxSum);
    }
}