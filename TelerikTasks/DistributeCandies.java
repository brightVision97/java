import java.util.HashSet;
import java.util.Set;

/**
 * Given an integer array with even length, where different numbers
 * in this array represent different kinds of candies.
 * Each number means one candy of the corresponding kind.
 * You need to distribute these candies equally in number to brother and sister.
 * Return the maximum number of kinds of candies the sister could gain.
 * <p>
 * Example:
 * <p>
 * Input: candies = [1,1,2,2,3,3]
 * Output: 3
 * Explanation:
 * There are three different kinds of candies (1, 2 and 3), and two candies for each kind.
 * Optimal distribution: The sister has candies [1,2,3] and the brother has candies [1,2,3], too.
 * The sister has three different kinds of candies.
 */
public class DistributeCandies
{
    public static int distributeCandies(int[] candies)
    {
        Set<Integer> differentCandies = new HashSet<>();
        
        for (int candy : candies)
            differentCandies.add(candy);
        
        return Math.min(differentCandies.size(), candies.length / 2);
    }
    
    public static void main(String[] args)
    {
        System.out.println(distributeCandies(new int[]{1, 1, 2, 2, 3, 3}));
    }
}
