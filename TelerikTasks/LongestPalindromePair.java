import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * A palindrome is a nonempty string over some alphabet that reads the same forward
 * and backward. Examples of palindromes are all strings of length 1, civic,
 * racecar, and aibohphobia (fear of palindromes).
 * <p>
 * Write a program that generates all possible pairs of words and finds the longest
 * palindrome pair (excluding the whitespace char between them).
 */
public class LongestPalindromePair
{
    private static List<String> pairFinder(List<String> input)
    {
        final List<String> pairs = new ArrayList<>();
        
        for (int i = 0; i < input.size(); ++i)
            for (int j = i + 1; j < input.size(); ++j)
                pairs.add(input.get(i) + " " + input.get(j));
        
        return pairs;
    }
    
    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);
        
        String[] inputWords = input.nextLine().trim().split("\\s");
        
        List<String> pairs = pairFinder(Arrays.asList(inputWords));
        
        int longestPairLength = 0;
        for (final String pair : pairs)
        {
            String word = pair.replace(" ", "");
            StringBuilder reversed = new StringBuilder(word).reverse();
            
            if (word.equals(reversed.toString()))
                if (word.length() > longestPairLength)
                    longestPairLength = word.length();
        }
        
        System.out.println(longestPairLength);
    }
}