import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class SubsetOfSumS
{
    static void fakeInput()
    {
        String input = "14\n" +
                "2 1 2 4 3 5 2 6";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    public static void main(String[] args)
    {
        fakeInput();
        Scanner in = new Scanner(System.in);
        
        int sum = in.nextInt();
        in.nextLine();

        /* using method reference and nested method invocation to generate
         an integer array from the input line while also sorting it */
        Integer[] elements = Arrays.stream(in.nextLine().split("\\s"))
                .map(Integer::parseInt).sorted().toArray(Integer[]::new);

        /* the upper row which we imagine above the first row of the matrix
           contatining all the digits up to the target sum, in other words
           all the possible sums that the subset'simplechess values could add up to */
        int[] digitsToSum = new int[sum + 1];
        for (int i = 0; i < digitsToSum.length; i++)
            digitsToSum[i] = i;

        /* the matrix itself; row count is the size of the elements array and
           column count is the size of the digitsToSum array */
        boolean[][] matrix = new boolean[elements.length][digitsToSum.length];

        /* the first all true column; same as the top row, we can
           imagine this one on the left side of the matrix */
        for (int i = 0; i < elements.length; i++)
            matrix[i][0] = true;

        /* setting the only other true value for the first row, the cell address that
           matches the corresponding number from top row with the one in the numbers column */
        for (int i = 1; i < digitsToSum.length; i++)
            if (digitsToSum[i] == elements[0])
                matrix[0][i] = true;
        
        // the main logic of the algorithm
        for (int i = 1; i < elements.length; i++)
        {
            for (int j = 1; j < digitsToSum.length; j++)
            {
                /* if the corresponding number from the top row matches
                 the one from the left column, we set the cell to true */
                if (elements[i] == digitsToSum[j])
                    matrix[i][j] = true;
                /* if the corresponding number from the top row is greater
                   we copy all the remaining values from the above row
                   starting from the current index onwards */
                else if (elements[i] > digitsToSum[j])
                    matrix[i][j] = matrix[i - 1][j];
                else
                {
                    if (matrix[i - 1][j]) // if the cell above is true
                    {
                        matrix[i][j] = true;
                        continue;
                    }
                    
                    // shifting one above and elements[i] times to the left
                    matrix[i][j] = matrix[i - 1][j - elements[i]];
                }
            }
        }
        
        // if the lower right corner cell is true, we have a matching subset
        if (matrix[elements.length - 1][sum])
            System.out.println("yes");
        else
            System.out.println("no");
    }
}