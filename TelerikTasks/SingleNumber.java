import java.util.HashSet;
import java.util.Set;

/**
 * Given a non-empty array of integers, every
 * element appears twice except for one.
 * Find that single one.
 * <p>
 * Example :
 * <p>
 * Input: [2,2,1]
 * Output: 1
 */
public class SingleNumber
{
    public static int singleNumber(int[] nums)
    {
        Set<Integer> result = new HashSet<>();
        
        for (int num : nums)
        {
            if (result.contains(num))
                result.remove(num);
            else
                result.add(num);
        }
        
        return (int) result.toArray()[0];
    }
    
    public static void main(String[] args)
    {
        System.out.println(singleNumber(new int[]{2, 2, 1}));
    }
}
