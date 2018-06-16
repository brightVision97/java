public class WildcardMatching
{
    static boolean isMatch(String str, String pattern)
    {
        int strPointer = 0;
        int patternPointer = 0;
        int match = 0;
        int starIndex = -1;

        while (strPointer < str.length())
        {
            // advancing both pointers
            if (patternPointer < pattern.length()  &&
                    (pattern.charAt(patternPointer) == '?' ||
                            str.charAt(strPointer) == pattern.charAt(patternPointer)))
            {
                strPointer++;
                patternPointer++;
            }
            // * found, only advancing pattern pointer
            else if (patternPointer < pattern.length() &&
                    pattern.charAt(patternPointer) == '*')
            {
                starIndex = patternPointer;
                match = strPointer;
                patternPointer++;
            }
            // last pattern pointer was *, advancing string pointer
            else if (starIndex != -1)
            {
                patternPointer = starIndex + 1;
                match++;
                strPointer = match;
            }
            /* current pattern pointer is not *, last pattern
             pointer was not * - >characters do not match */
            else
                return false;
        }

        // check for remaining characters in the pattern
        while (patternPointer < pattern.length() && pattern.charAt(patternPointer) == '*')
            patternPointer++;

        return patternPointer == pattern.length();
    }

    public static void main(String[] args)
    {
        System.out.println(isMatch("acdcb", "a*c?*b"));
    }
}