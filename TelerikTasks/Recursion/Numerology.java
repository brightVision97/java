package Recursion;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class Numerology
{
    static void fakeInput()
    {
        String input = "19970328";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    static int[] result;

    private static void countDigits(int[] digits)
    {
        if (digits.length == 1)
        {
            result[digits[0]]++;
            return;
        }

        for (int i = 0; i < digits.length - 1; i++)
        {
            int[] newDigits = new int[digits.length - 1];
            for (int j = 0; j < i; j++)
                newDigits[j] = digits[j];

            newDigits[i] = calculateDigit(digits[i], digits[i + 1]);
            for (int j = i + 2; j < digits.length; j++)
                newDigits[j - 1] = digits[j];

            countDigits(newDigits);
        }
    }

    static int calculateDigit(int a, int b)
    {
        return ((a + b) * (a ^ b)) % 10;
    }

    public static void main(String[] args)
    {
        fakeInput();
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        result = new int[10];

        int[] digits = new int[8];

        int i = digits.length - 1;
        while (n > 0)
        {
            digits[i--] = n % 10;
            n /= 10;
        }

        countDigits(digits);

        for (int num : result)
            System.out.print(num + " ");
    }
}
