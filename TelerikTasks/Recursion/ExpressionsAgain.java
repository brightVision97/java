package Recursion;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class ExpressionsAgain
{
    static void fakeInput()
    {
        String input = "123\n" +
                "6";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    static int targetSum;

    public static int calculate(int[] digits, int currentSum)
    {
        if  (digits.length == 0)
            return targetSum == currentSum ? 1 : 0;

        int count = 0;
        int number = 0;
        for (int i = 0; i < digits.length; i++)
        {
            number *= 10;
            number += digits[i];

            int[] numbers = new int[digits.length - 1];
            for (int j = i + 1; j < digits.length; j++)
                numbers[j - 1] = digits[j];

            count += calculate(numbers, currentSum + number);
            count += calculate(numbers, currentSum * number);
            count += calculate(numbers, currentSum - number);

            if (number == 0)
                break;
        }

        return count;
    }

    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);

        int[] numbers = Arrays.stream(input.nextLine().split(""))
                .mapToInt(Integer::parseInt)
                .toArray();

        targetSum = input.nextInt();

        int number = 0;
        int result = 0;

        for (int i = 0; i < numbers.length; i++)
        {
            number *= 10;
            number += numbers[i];

            int[] newNumbers = new int[numbers.length - 1 - i];

            int index = 0;
            for (int j = i + 1; j < numbers.length; j++)
                newNumbers[index++] = numbers[j];

            result += calculate(newNumbers, number);

            if (number == 0)
                break;
        }

        System.out.println(result);
    }
}
