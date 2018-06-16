package Recursion;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class Permutations
{
    static void fakeInput()
    {
        String input = "5";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    static int[] fillSet(int[] set)
    {
        for (int i = 0; i < set.length; i++)
            set[i] = i + 1;

        return set;
    }

    static void generatePermutations(int index, int[] set, int[] result, boolean[] used)
    {
        if (index == result.length)
            printPermutations(result);
        else
        {
            for (int i = 0; i < result.length; i++)
            {
                if (!used[i])
                {
                    used[i] = true;
                    result[index] = set[i];

                    generatePermutations(index + 1, set, result, used);

                    used[i] = false;
                }
            }
        }

    }

    static void printPermutations(int[] set)
    {
        for (int i = 0; i < set.length - 1; i++)
            System.out.print(set[i] + " ");

        System.out.println(set[set.length - 1]);
    }

    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        int[] set = new int[n];
        int[] result = new int[n];
        boolean[] used = new boolean[n];

        fillSet(set);
        generatePermutations(0, set, result, used);
    }
}