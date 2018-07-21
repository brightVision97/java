import java.util.HashSet;
import java.util.Set;

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
