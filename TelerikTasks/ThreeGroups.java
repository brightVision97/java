import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class ThreeGroups
{
    static void fakeInput()
    {
        String input = "1 2 3 4 5 6 7";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);

        Integer[] array = Arrays.stream(input.nextLine().split("\\s"))
                .map(Integer::parseInt).toArray(Integer[]::new);

        int remainder0 = 0;
        int remainder1 = 0;
        int remainder2 = 0;

        for (int i = 0; i < array.length; i++)
        {
            if (array[i] % 3 == 0)
                remainder0++;
            else if (array[i] % 3 == 1)
                remainder1++;
            else if (array[i] % 3 == 2)
                remainder2++;
        }

        int[][] matrix =
                {
                        new int[remainder0],
                        new int[remainder1],
                        new int[remainder2]
                };

        int indexer = 0;
        for (int i = 0; i < array.length; i++)
            if (array[i] % 3 == 0)
                matrix[0][indexer++] = array[i];

        indexer = 0;
        for (int i = 0; i < array.length; i++)
            if (array[i] % 3 == 1)
                matrix[1][indexer++] = array[i];

        indexer = 0;
        for (int i = 0; i < array.length; i++)
            if (array[i] % 3 == 2)
                matrix[2][indexer++] = array[i];

        for (int[] row : matrix)
        {
            for (int col : row)
                System.out.print(col + " ");
            System.out.println();
        }
    }
}