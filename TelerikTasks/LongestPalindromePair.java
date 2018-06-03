import java.util.*;

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