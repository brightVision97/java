import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Given a sorted array of integers, find the starting and ending position
 * of a given target value. Your algorithm's runtime complexity must be in
 * the order of O(log n). If the target is not found in the array, return [-1, -1].
 * <p>
 * For example:
 * <p>
 * We are given [5, 7, 7, 8, 8, 10] and target value 8
 * => return array is [3, 4]
 */
public class SearchForRange
{
    static int[] searchRange(int[] nums, int target)
    {
        int start = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toList())
                .indexOf(target);
        
        if (start == -1)
            return new int[]{start, -1};
        
        int end = start;
        for (int i = start + 1; i < nums.length; i++)
            if (nums[i] == target)
                end = i;
        
        return new int[]{start, end};
    }
    
    public static void main(String[] args)
    {
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 8, 8, 10}, 5)));
    }
}