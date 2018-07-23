package recursion;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S.
 * Now you have 2 symbols + and -. For each integer,
 * you should choose one from + and - as its new symbol.
 * <p>
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 * <p>
 * Example:
 * <p>
 * Input:
 * nums is [1, 1, 1, 1, 1], S is 3.
 * Output:
 * 5
 * Explanation:
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * <p>
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 */
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
