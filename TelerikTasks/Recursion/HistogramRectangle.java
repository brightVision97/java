package Recursion;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class HistogramRectangle
{
    static void fakeInput()
    {
        String input = "4 7 5 9 1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    static int solve(int[] heights)
    {
        if (heights.length == 0)
            return 0;

        int indexOfMin = findIndexOfMin(heights);
        int minArea = heights[indexOfMin] * heights.length;

        int[] leftSideArr = Arrays.copyOfRange(heights, 0, indexOfMin);
        int[] rightSideArr = Arrays.copyOfRange(heights, indexOfMin + 1, heights.length);

        return Math.max(Math.max(solve(rightSideArr), solve(leftSideArr)), minArea);
    }

    static int findIndexOfMin(int[] heights)
    {
        if (heights.length == 1)
            return 0;

        int minIndex = 0;
        for (int i = 1; i < heights.length; i++)
            if (heights[i] < heights[minIndex])
                minIndex = i;

        return minIndex;
    }

    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);

        int[] heights = Arrays.stream(input.nextLine().split("\\s"))
                .mapToInt(Integer::parseInt)
                .toArray();

        System.out.println(solve(heights));
    }
}
