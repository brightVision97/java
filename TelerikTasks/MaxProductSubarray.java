/**
 * Given an integer array nums, find the contiguous subarray within an array
 * (containing at least one number) which has the largest product
 * <p>
 * Example:
 * <p>
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 */
public class MaxProductSubarray
{
    static int maxProduct(int[] nums)
    {
        if (nums == null || nums.length == 0)
            return 0;
        
        int max = nums[0];
        int min = nums[0];
        int result = nums[0];
        
        for (int i = 1; i < nums.length; i++)
        {
            int temp = max;
            
            max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
            min = Math.min(Math.min(temp * nums[i], min * nums[i]), nums[i]);
            
            if (max > result)
                result = max;
            
        }
        
        return result;
    }
    
    public static void main(String[] args)
    {
        System.out.println(maxProduct(new int[]{-2, 0, -1}));
    }
}