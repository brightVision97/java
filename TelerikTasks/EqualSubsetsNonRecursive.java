import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Given an array of integers nums and a positive integer k,
 * find whether it's possible to divide this array
 * into k non-empty subsets whose sums are all equal.
 * <p>
 * Example:
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: True
 * Explanation: It's possible to divide it into 4 subsets with equal sums:
 * (5), (1, 4), (2,3), (2,3)
 */
public class EqualSubsetsNonRecursive
{
    static void fakeInput()
    {
        String input = "4, 1, 2, 2, 1, 4, 1, 4, 1\n" +
                "4";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    Map<Integer, Integer> map = new HashMap<>();
    
    static boolean canPartitionKSubsets(int[] nums, int k)
    {
        if (nums == null || nums.length == 0 || k == 0)
            return false;
        
        Arrays.sort(nums);
        
        int sum = IntStream.of(nums).sum();
        
        if (sum % k != 0 || sum < k)
            return false;
        
        return isPossible(nums, sum / k, new int[k], nums.length - 1);
    }
    
    static boolean isPossible(int[] nums, int sum, int[] p, int idx)
    {
        if (idx == -1)
        {
            for (int s : p)
                if (s != sum)
                    return false;
            
            return true;
        }
        
        int num = nums[idx];
        
        for (int i = 0; i < p.length; i++)
        {
            if (p[i] + num <= sum)
            {
                p[i] += num;
                
                if (isPossible(nums, sum, p, idx - 1))
                    return true;
                
                p[i] -= num;
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) throws IOException
    {
        fakeInput();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        int[] nums = Arrays.stream(bf.readLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();
        
        int k = Integer.parseInt(bf.readLine());
        
        System.out.println(canPartitionKSubsets(nums, k));
    }
}