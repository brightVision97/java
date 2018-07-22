import java.util.HashSet;
import java.util.Set;

/**
 * You're given strings J representing the types of stones that are jewels,
 * and S representing the stones you have.  Each character in S is a type of stone you have.
 * You want to know how many of the stones you have are also jewels.
 * <p>
 * The letters in J are guaranteed distinct, and all characters in J and S are letters.
 * Letters are case sensitive, so "a" is considered a different type of stone from "A".
 * <p>
 * Example:
 * <p>
 * Input: J = "aA", S = "aAAbbbb"
 * Output: 3
 */
public class JewelsAndStones
{
    public static int numJewelsInStones(String J, String S)
    {
        char[] letters = J.toCharArray();
        
        Set<Character> jewels = new HashSet<>();
        
        for (char ch : letters)
            jewels.add(ch);
        
        int count = 0;
        
        for (char ch : S.toCharArray())
            if (jewels.contains(ch))
                ++count;
        
        return count;
    }
    
    public static void main(String[] args)
    {
        System.out.println(numJewelsInStones("aA", "aAAbbbb"));
        System.out.println(numJewelsInStones("z", "ZZ"));
    }
}
