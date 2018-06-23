public class WildcardMatching
{
    static boolean isMatch(String str, String pattern)
    {
        int strPointer = 0; // cursor for traversal in string
        int patternPointer = 0; // cursor for traversal in pattern
        int match = 0; // once we found a star, we want to record the place of the star
        int starIndex = -1; /* once we found a star, we want to start to match the rest
                            // of pattern with str, starting from match; this is for
                            // remembering the place where we need to start */

        // we check and match every char for the string
        while (strPointer < str.length())
        {
            // case 1. we are not currently at any * -> advancing both pointers
            if (patternPointer < pattern.length()  &&
                    (pattern.charAt(patternPointer) == '?' ||
                            str.charAt(strPointer) == pattern.charAt(patternPointer)))
            {
                strPointer++;
                patternPointer++;
            }
            // case 2. * found -> only advancing pattern pointer
            else if (patternPointer < pattern.length() &&
                    pattern.charAt(patternPointer) == '*')
            {
                starIndex = patternPointer;
                match = strPointer;
                patternPointer++;
            }
            /* case 3. they do not match, we are not currently at a *,
               but the last matched is a * -> advancing string pointer */
            else if (starIndex != -1)
            {
                patternPointer = starIndex + 1;
                match++;
                strPointer = match;
            }
            /* case 4. current pattern pointer is not *, last pattern
             pointer was not * -> characters do not match */
            else
                return false;
        }
        /* when all chars from the word are matched, is the pattern also finished?
           -> we can only allow '*' for the rest of pattern */
        while (patternPointer < pattern.length() && pattern.charAt(patternPointer) == '*')
            patternPointer++;

        return patternPointer == pattern.length();
    }
    
    static boolean isMatchDP(String word, String pattern)
    {
        char[] wordChars = word.toCharArray();
        char[] patternChars = pattern.toCharArray();
        
        // the following fragment replaces multiple consecutive stars with just one
        // example -> "a***b***c" to "a*b*c"
        int writeIndex = 0;
        boolean isFirst = true;
        for (int i = 0; i < pattern.length(); i++)
        {
            if (patternChars[i] == '*')
            {
                if (isFirst)
                {
                    patternChars[writeIndex++] = patternChars[i];
                    isFirst = false;
                }
            } else
            {
                patternChars[writeIndex++] = patternChars[i];
                isFirst = true;
            }
        }
        
        boolean[][] dp = new boolean[word.length() + 1][writeIndex + 1];
        
        /* dp[0][1] is only ever going to be true when
           the first char from the pattern is a star */
        if (writeIndex > 0 && patternChars[0] == '*')
            dp[0][1] = true;
        
        // empty word on an empty pattern -> true
        dp[0][0] = true;
        
        for (int i = 1; i < dp.length; i++)
        {
            for (int j = 1; j < dp[0].length; j++)
            {
                if (wordChars[i - 1] == patternChars[j - 1] || patternChars[j - 1] == '?')
                    dp[i][j] = dp[i - 1][j - 1];
                else if (patternChars[j - 1] == '*')
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
            }
        }
        
        return dp[word.length()][writeIndex];
    }

    public static void main(String[] args)
    {
        System.out.println(isMatchDP("acdcb", "a*c?*b"));
    }
}