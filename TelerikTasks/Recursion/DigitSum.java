package recursion;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class DigitSum
{
    static void fakeInput()
    {
        String input = "9875 4";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    static int solve(String number, int k)
    {
        if (number.length() == 1)
            return Integer.parseInt(number);

        int digitsSum = 0;
        for (int i = 0; i < number.length(); i++)
            digitsSum += Character.getNumericValue(number.charAt(i));

        return solve(String.valueOf(digitsSum), k);
    }

    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);

        String[] inputData = input.nextLine().split("\\s");

        StringBuilder superDigit = new StringBuilder();

        int k = Integer.parseInt(inputData[1]);
        for (int i = 0; i < k; i++)
            superDigit.append(inputData[0]);

        System.out.println(solve(superDigit.toString(), k));
    }
}