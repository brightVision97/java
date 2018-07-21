import java.util.HashSet;
import java.util.Set;

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
