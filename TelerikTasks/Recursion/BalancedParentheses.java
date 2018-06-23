package recursion;

public class BalancedParentheses
{
    static void printParentheses(char[] result, int pos, int n, int open, int close)
    {
        if (close == n)
            printCombinations(result);
        else
        {
            if (open > close)
            {
                result[pos] = ')';
                printParentheses(result, pos + 1, n, open, close + 1);
            }

            if (open < n)
            {
                result[pos] = '(';
                printParentheses(result, pos + 1, n, open + 1, close);
            }
        }
    }

    static void printCombinations(char[] result)
    {
        for (int i = 0; i < result.length; i++)
            System.out.print(result[i]);

        System.out.println();
    }

    public static void main(String[] args)
    {
        int n = 8;
        n /= 2;

        char[] result = new char[n * 2];

        printParentheses(result, 0, n, 0, 0);
    }
}