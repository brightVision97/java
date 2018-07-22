import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * We are given a matrix of strings of size N x M.
 * Sequences in the matrix we define as sets of several
 * neighbour elements located on the same line, column or diagonal.
 * Write a program that finds the longest sequence of equal
 * strings in the matrix and prints its length.
 */
public class LongestMatrixSequence
{
    static void fakeInput()
    {
        String input = "6 6\n" +
                "92 11 23 42 59 48\n" +
                "09 92 23 72 56 14\n" +
                "17 63 92 46 85 95\n" +
                "34 12 52 69 23 95\n" +
                "26 88 78 71 29 95\n" +
                "26 34 16 63 39 95";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    static int[][] changes =
            {
                    {0, 1},
                    {1, 1},
                    {-1, 1},
                    {1, 0},
            };
    
    static int findLongestSequence(int[][] matrix, int bestElement, int bestLength)
    {
        for (int row = 0; row < matrix.length; row++)
        {
            for (int col = 0; col < matrix[0].length; col++)
            {
                int direction = -1;
            
                /* directions:
                   0 (right)
                   1 (down diagonal)
                   2 (up diagonal)
                   3 (down) */
                while (++direction < 4)
                {
                    int currentRow = row + changes[direction][0];
                    int currentCol = col + changes[direction][1];
                    
                    int currentLength = 1;
                    
                    while (isInRangeAndEqualTo(matrix, row, col, currentRow, currentCol))
                    {
                        currentLength++;
                        
                        if (currentLength > bestLength)
                        {
                            bestLength = currentLength;
                            bestElement = matrix[row][col];
                        }
                        
                        currentRow += changes[direction][0];
                        currentCol += changes[direction][1];
                    }
                }
            }
        }
        
        return bestLength;
    }
    
    static boolean isInRangeAndEqualTo(int[][] matrix, int row, int col,
                                       int currentRow, int currentCol)
    {
        return currentRow >= 0 && currentRow < matrix.length &&   // in range of rows
                currentCol >= 0 && currentCol < matrix[0].length && // in range of cols
                matrix[currentRow][currentCol] == matrix[row][col];
    }
    
    public static void main(String[] args) throws IOException
    {
        fakeInput();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] matrixSize = br.readLine().split("\\s");
        
        int rows = Integer.parseInt(matrixSize[0]);
        int cols = Integer.parseInt(matrixSize[1]);
        
        int[][] matrix = new int[rows][cols];
        
        for (int i = 0; i < rows; i++)
            matrix[i] = Arrays.stream(br.readLine().split("\\s"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        
        System.out.println(findLongestSequence(matrix, 0, 0));
    }
}