import java.io.ByteArrayInputStream;
import java.util.Scanner;

/**
 * You are given numbers N and M. They form a matrix of the powers of 2.
 * <p>
 * Example:
 * <p>
 * N = 3, M = 4
 * <p>
 * 1 2 4 8
 * 2 4 8 16
 * 4 8 16 32
 * <p>
 * Starting from the top left corner of the matrix,
 * Go with diagonal moves, until you hit a wall.
 * When a wall is hit, change direction.
 * You do this, until the direction cannot be changed,
 * i.e. you hit a corner.
 * <p>
 * If you have the above matrix, the path will be: 1 4 16 16 4 4 4
 */
public class Bounce
{
    static void fakeInput()
    {
        String test = "3 4";
        
        System.setIn(new ByteArrayInputStream(test.getBytes()));
    }
    
    public static void main(String[] args)
    {
        //fakeInput();
        Scanner input = new Scanner(System.in);
        
        int n = input.nextInt();
        int m = input.nextInt();
        
        long[] powersOfTwo = new long[n + m - 1];
        powersOfTwo[0] = 1;
        for (int i = 1; i < n + m - 1; i++)
            powersOfTwo[i] = powersOfTwo[i - 1] * 2;
        
        int currentRow = 0;
        int currentCol = 0;
        int horizontal = 1;
        int vertical = 1;
        long result = 1;
        
        result = powersOfTwo[0];
        while (true)
        {
            int nextRow = currentRow + vertical;
            int nextCol = currentCol + horizontal;
            
            if (nextRow < 0 || n <= nextRow)
                vertical *= -1;
            if (nextCol < 0 || m <= nextCol)
                horizontal *= -1;
            
            currentRow += vertical;
            currentCol += horizontal;
            
            if (currentRow < 0 || currentRow >= n ||
                    currentCol < 0 || currentCol >= m)
                break;
            
            result += powersOfTwo[currentRow + currentCol];
            
            if ((currentRow == 0 && currentCol == 0) ||
                    (currentRow == 0 && currentCol == m - 1) ||
                    (currentRow == n - 1 && currentCol == 0) ||
                    (currentRow == n - 1 && currentCol == m - 1))
                break;
        }
        
        System.out.println(result);
    }
}
