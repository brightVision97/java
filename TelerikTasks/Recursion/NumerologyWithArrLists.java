package Recursion;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NumerologyWithArrLists
{
    static void fakeInput()
    {
        String input = "19970328";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    static void count(List<Integer> digits, int[] digitsCounter)
    {
        if (digits.size() == 1)
        {
            digitsCounter[digits.get(0)]++;
            return;
        }

        for (int i = 1; i < digits.size(); i++)
        {
            int digitA = digits.get(i - 1);
            int digitB = digits.get(i);

            digits.set(i - 1, calculateDigit(digitA, digitB));
            digits.remove(i);

            count(digits, digitsCounter);

            digits.set(i - 1, digitA);
            digits.add(i, digitB);
        }
    }

    static List<Integer> stringArrToIntList(String[] input)
    {
        return Arrays.stream(input)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    static int calculateDigit(int a, int b)
    {
        return ((a + b) * (a ^ b)) % 10;
    }

    static void printArray(int[] digitsCounter)
    {
        Arrays.stream(digitsCounter)
                .forEach(x -> System.out.print(x + " "));
    }

    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);

        List<Integer> digits = stringArrToIntList(input.nextLine().split(""));

        int[] digitsCounter = new int[10];

        count(digits, digitsCounter);
        printArray(digitsCounter);
    }
}