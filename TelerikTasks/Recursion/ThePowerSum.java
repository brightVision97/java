package recursion;

/**
 * Find the number of ways that a given integer X can be expressed
 * as the sum of the  powers of unique, natural numbers.
 * <p>
 * For example, if X=13 and N=2 , we have to find all combinations of unique
 * squares adding up to . The only solution is 2^2 + 3^2.
 */
public class ThePowerSum
{
    static int powerSum(int x, int n, int num)
    {
        int currentPow = (int) Math.pow(num, n);
        
        if (currentPow < x)
            return powerSum(x, n, num + 1) +
                    powerSum(x - currentPow, n, num + 1);
        
        return currentPow == x ? 1 : 0;
    }
    
    public static void main(String[] args)
    {
        System.out.println(powerSum(100, 2, 1));
    }
}