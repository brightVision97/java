package recursion;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class TargetSum
{
    static void fakeInput()
    {
        String input = "1 1 1 1 1\n" +
                "3";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    static int findTargetSumWays(int[] nums, int S)
    {
        return solve(0, nums, 0, S);
    }

    static int solve(int index, int[] numbers, int current, int target)
    {
        if (index == numbers.length)
        {
            if (current == target)
                return 1;

            return 0;
        }

        int counter = 0;

        counter += solve(index + 1, numbers, current + numbers[index], target);
        counter += solve(index + 1, numbers, current - numbers[index], target);

        return counter;
    }

    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);

        int[] numbers = Arrays.stream(input.nextLine().split("\\s"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int targetSum = input.nextInt();

        System.out.println(findTargetSumWays(numbers, targetSum));
    }
}
