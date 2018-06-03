import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.util.Scanner;

public class BitShiftMatrix
{
    static void fakeInput()
    {
        String test = "5 \n" +
                "6\n" +
                "4\n" +
                "14 27 1 5";

        System.setIn(new ByteArrayInputStream(test.getBytes()));
    }

    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);

        int rows = input.nextInt();
        int cols = input.nextInt();
        int n = input.nextInt();

        int[] cells = new int[n];
        for (int i = 0; i < n; i++)
            cells[i] = input.nextInt();

        BigInteger[] powersOfTwo = new BigInteger[rows + cols - 1];
        BigInteger powerOfTwo = BigInteger.ONE;
        for (int i = 0; i < rows + cols - 1; i++)
        {
            powersOfTwo[i] = powerOfTwo;
            powerOfTwo = powerOfTwo.multiply(BigInteger.TWO);
    }

        boolean[][] matrix = new boolean[rows][cols];
        int coeff = Math.max(rows, cols);
        int currentRow = rows - 1;
        int currentCol = 0;
        BigInteger result = BigInteger.ZERO;

        for (int cell : cells)
        {
            int targetRow = cell / coeff;
            int targetCol = cell % coeff;

            int horizontalDirection;
            if (currentCol <= targetCol)
                horizontalDirection = 1;
            else
                horizontalDirection = -1;

            int verticalDirection;
            if (currentRow <= targetRow)
                verticalDirection = 1;
            else
                verticalDirection = -1;

            while (currentCol != targetCol || currentRow != targetRow)
            {
                if (!matrix[currentRow][currentCol])
                {
                    matrix[currentRow][currentCol] = true;
                    int pow = rows - currentRow + currentCol - 1;
                    result = result.add(powersOfTwo[pow]);
                }

                if (currentCol != targetCol)
                {
                    currentCol += horizontalDirection;
                    continue;
                }

                if (currentRow != targetRow)
                    currentRow += verticalDirection;
            }
        }

        if (!matrix[currentRow][currentCol])
        {
            matrix[currentRow][currentCol] = true;
            int pow = rows - currentRow + currentCol - 1;
            result = result.add(powersOfTwo[pow]);
        }

        System.out.println(result);
    }
}
