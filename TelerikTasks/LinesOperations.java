import java.util.Arrays;
import java.util.Scanner;

public class LinesOperations
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        String[] nm = input.nextLine().trim().split("\\s");

        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        int[] lineStartes = new int[n];

        for (int i = 0; i < n; i++)
            lineStartes[i] = input.nextInt();

        int finalSum = 0;
        int currentLinePosition = 0;
        for (int i = 0; i < n; i++)
        {
            if (currentLinePosition == 3)
                currentLinePosition = 0;

            if (currentLinePosition == 0)
                finalSum += lineStartes[i];
            else if (currentLinePosition == 1)
                finalSum += lineStartes[i] + m - 1;
            else if (currentLinePosition == 2)
            {
                int lineSum = 0;
                for (int j = 0; j < m; j++)
                    lineSum += lineStartes[i]++;

                finalSum += (lineSum / m);
            }

            currentLinePosition++;
        }

        System.out.println(finalSum);
    }
}