package recursion;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GreedyDwarf
{
    static void fakeInput()
    {
        String input = "3 3\n" +
                "10 10 0\n" +
                "10 10 10\n" +
                "10 10 10";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    static int[] nextDirection(int[][] field, int row, int col)
    {
        int[] adjCells = new int[4];
        
        if (col - 1 >= 0)
            adjCells[0] = field[row][col - 1];
        if (col + 1 < field[0].length)
            adjCells[1] = field[row][col + 1];
        if (row - 1 >= 0)
            adjCells[2] = field[row - 1][col];
        if (row + 1 < field.length)
            adjCells[3] = field[row + 1][col];
        
        int max = Math.max(Math.max(Math.max(adjCells[0], adjCells[1]), adjCells[2]), adjCells[3]);
        
        if (max == adjCells[0])
            return new int[] {row, col - 1};
        else if (max == adjCells[1])
            return new int[] {row, col + 1};
        else if (max == adjCells[2])
            return new int[] {row - 1, col};
        
        return new int[] {row + 1, col};
    }
    
    static int calculateCoins(int[][] field, int row, int col)
    {
        if (row >= field.length || col >= field[0].length ||
                row < 0 || col < 0)
            return 0;
        
        if (field[row][col] == 0)
            return 0;
        
        if (field[row][col] > 0)
            field[row][col]--;
        
        int[] nextCellDirection = nextDirection(field, row, col);
        
        return calculateCoins(field, nextCellDirection[0], nextCellDirection[1]) + 1;
    }
    
    static int[] findStartCell(int[][] field)
    {
        for (int row = 0; row < field.length; row++)
            for (int col = 0; col < field[row].length; col++)
                if (field[row][col] == 0)
                    return new int[] {row, col};
        
        return null;
    }
    
    public static void main(String[] args) throws IOException
    {
        fakeInput();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        
        int[] fieldIndices = Arrays.stream(input.readLine().split("\\s"))
                .mapToInt(Integer::parseInt)
                .toArray();
        
        int[][] field = new int[fieldIndices[0]][fieldIndices[1]];
        
        for (int i = 0; i < fieldIndices[0]; i++)
            field[i] = Arrays.stream(input.readLine().split("\\s"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        
        int[] startCellIndices = findStartCell(field);
        int[] nextCellDirection = nextDirection(field, startCellIndices[0], startCellIndices[1]);
        
        System.out.println(calculateCoins(field, nextCellDirection[0], nextCellDirection[1]));
    }
}