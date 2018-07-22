package recursion;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Write a program that finds the largest area of equal neighbour
 * elements in a rectangular matrix and prints its size.
 */
public class LargestMatrixArea
{
    static void fakeInput()
    {
        String input = "5 6\n" +
                "1 3 2 2 2 4\n" +
                "3 3 3 2 4 4\n" +
                "4 3 1 2 3 3\n" +
                "4 3 1 3 3 1\n" +
                "4 3 3 3 1 1\t";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    static int[][] matrix;
    static int currentLength = 0;
    static int currentElement = 0;
    
    static int findLargestAreaLength(int[][] matrix)
    {
        int bestLength = 0;
        for (int row = 0; row < matrix.length; row++)
        {
            for (int col = 0; col < matrix[row].length; col++)
            {
                currentElement = matrix[row][col];
                currentLength = 0;
                
                getCurrentAreaLength(row, col);
                
                if (currentLength > bestLength)
                    bestLength = currentLength;
            }
        }
        
        return bestLength;
    }
    
    static void getCurrentAreaLength(int row, int col)
    {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length ||
                matrix[row][col] == Integer.MIN_VALUE)
            return;
        
        if (matrix[row][col] == currentElement)
        {
            matrix[row][col] = Integer.MIN_VALUE;
            
            currentLength++;
            
            getCurrentAreaLength(row - 1, col); // up
            getCurrentAreaLength(row + 1, col); // down
            getCurrentAreaLength(row, col - 1); // left
            getCurrentAreaLength(row, col + 1); // right
        }
    }
    
    public static void main(String[] args) throws IOException
    {
        fakeInput();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] matrixSize = br.readLine().split("\\s");
        
        int rows = Integer.parseInt(matrixSize[0]);
        int cols = Integer.parseInt(matrixSize[1]);
        
        if (matrix == null)
            matrix = new int[rows][cols];
        
        for (int i = 0; i < rows; i++)
            matrix[i] = Arrays.stream(br.readLine().split("\\s"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        
        System.out.println(findLargestAreaLength(matrix));
    }
}