import java.util.*;

public class AlphaNumbers
{
    public static void main(String[] args)
    {
        int n = 3;

        Scanner input = new Scanner(System.in);

        String[] numbers = new String[n];

        List<Integer> alphaNumbers = new ArrayList<>();

        for (int i = 0; i < n; i++)
            numbers[i] = input.nextLine();

        for (int i = 0; i < numbers.length; i++)
        {
            int x = numbers[i].charAt(0) - '0';
            int y = numbers[i].charAt(1) - '0';
            int z = numbers[i].charAt(2) - '0';

            String sumOfDigits = String.valueOf(x + y + z);

            int lastSumDigit = sumOfDigits.charAt(sumOfDigits.length() - 1) - '0';

            char[] sortedDigits = numbers[i].toCharArray();
            Arrays.sort(sortedDigits);

            List<Integer> nonNegativeDiffs = new ArrayList<>();

            nonNegativeDiffs.add(sortedDigits[2] - sortedDigits[1]);
            nonNegativeDiffs.add(sortedDigits[2] - sortedDigits[0]);
            nonNegativeDiffs.add(sortedDigits[1] - sortedDigits[0]);

            int biggestNonNegDiff = Collections.max(nonNegativeDiffs);

            if (biggestNonNegDiff > lastSumDigit)
                alphaNumbers.add(Integer.valueOf(numbers[i]));
        }

        if (input != null)
            input.close();

        System.out.println();

        for (int i = 0; i < alphaNumbers.size(); i++)
            System.out.println(alphaNumbers.get(i));
    }
}
