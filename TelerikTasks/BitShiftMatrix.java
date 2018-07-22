import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * You are given rectangular matrix. The matrix is filled with numbers that are power of 2, as follows:
 * <p>
 * The bottom left corner holds the value 1
 * <p>
 * The next cell above holds value of 2, the next cell above holds of 4, etc…
 * <p>
 * The second cell the bottom row holds a value of 2, the cell next to it holds a value of 4
 * <p>
 * You have a pawn on the field. The pawn can only move to the cells that directly above,
 * below it or right/left of it. We have four directions UP, DOWN, LEFT, RIGHT.
 * <p>
 * Given that initially the pawn starts at the bottom left corner and a list of cells
 * that the pawn must reach calculate the sum of the cells that the pawn has to go through.
 * <p>
 * The value of each cell is calculated only once, i.e. if the pawn visits
 * the same cell more than once, its value is added to the
 * result only the first time (the value is collected).
 * <p>
 * The top row is at position 0, the bottommost row is at position ROWS – 1.
 * <p>
 * The pawn goes from one cell to the other, following the rules:
 * <p>
 * First go to the target column
 * <p>
 * Second go to the target row
 * <p>
 * Example:
 * <p>
 * 16  32  64  128  256  512
 * 8   16  32  64   128  256
 * 4   8   16  32   64   128
 * 2   4   8   16   32   64
 * 1   2   4   8    16   32
 * <p>
 * The pawn collects values: 1, 2, 4, 8, 16, 32, 16, 8, 4, 8, 16, 32, 64, 128, 256 and 512.
 * Their sum is 1107.
 */
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
