import java.util.*;

public class HexNumbers
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        String hexConcat = "";

        for (int i = 0; i < n; i++)
            hexConcat += Integer.toHexString(input.nextInt());

        int currMaxLen = 1;
        int maxSeqLen = currMaxLen;
        int maxLenOccurTimes = 1;

        for (int i = 1; i < hexConcat.length(); i++)
        {
            char currChar = hexConcat.charAt(i);
            char prevChar = hexConcat.charAt(i - 1);

            if (currChar == prevChar)
            {
                currMaxLen++;

                if (i == hexConcat.length() - 1)
                {
                    if (currMaxLen > maxSeqLen)
                    {
                        maxSeqLen = currMaxLen;
                        maxLenOccurTimes = 1;
                    }
                    else if (currMaxLen == maxSeqLen)
                        maxLenOccurTimes++;

                    break;
                }
            }
            else
            {
                if (currMaxLen == maxSeqLen)
                    maxLenOccurTimes++;
                else if (currMaxLen > maxSeqLen)
                {
                    maxSeqLen = currMaxLen;
                    maxLenOccurTimes = 1;
                }

                currMaxLen = 1;
            }
        }

        if (input != null)
            input.close();

        System.out.println("\n" + hexConcat);
        System.out.println(maxSeqLen + " " + maxLenOccurTimes);
    }
}
