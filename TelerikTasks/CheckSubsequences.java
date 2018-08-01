import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Coki is a very naughty boy. He likes sequences and subsequences very much.
 * Today he wants to check what is the subsequence of characters that is longest
 * and is present in two strings.
 * <p>
 * HELP HIM, otherwise he will break all hell loose!
 */
public class CheckSubsequences
{
    private static int arr[][];
    
    private static void longestSubsequence(String str1, String str2)
    {
        for (int i = 1; i <= str1.length(); i++)
        {
            for (int j = 1; j <= str2.length(); j++)
            {
                if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    arr[i][j] = arr[i - 1][j - 1] + 1;
                else
                    arr[i][j] = Math.max(arr[i - 1][j], arr[i][j - 1]);
            }
        }
    }
    
    private static SortedSet<String> longestSubsequence(String str1, String str2, int len1, int len2)
    {
        if (len1 == 0 || len2 == 0)
        {
            SortedSet<String> set = new TreeSet<>(Comparator.naturalOrder());
            set.add("");
            
            return set;
        }
        
        if (str1.charAt(len1 - 1) == str2.charAt(len2 - 1))
        {
            SortedSet<String> set = longestSubsequence(str1, str2, len1 - 1, len2 - 1);
            SortedSet<String> set1 = new TreeSet<>(Comparator.naturalOrder());
            
            for (String temp : set)
            {
                temp = temp + str1.charAt(len1 - 1);
                set1.add(temp);
            }
            
            return set1;
        } else
        {
            SortedSet<String> set = new TreeSet<>(Comparator.naturalOrder());
            SortedSet<String> set1 = new TreeSet<>(Comparator.naturalOrder());
            
            if (arr[len1 - 1][len2] >= arr[len1][len2 - 1])
                set = longestSubsequence(str1, str2, len1 - 1, len2);
            
            if (arr[len1][len2 - 1] >= arr[len1 - 1][len2])
                set1 = longestSubsequence(str1, str2, len1, len2 - 1);
            
            for (String temp : set)
                set1.add(temp);
            
            return set1;
        }
    }
    
    private static void fakeInput()
    {
        String input = "sequences\n" +
                "sequences";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    public static void main(String[] args) throws IOException
    {
        fakeInput();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        String str1 = reader.readLine();
        String str2 = reader.readLine();
        
        arr = new int[str1.length() + 1][str2.length() + 1];
        
        longestSubsequence(str1, str2);
        
        System.out.println(longestSubsequence(
                str1, str2, str1.length(), str2.length()).toArray()[0]);
    }
}