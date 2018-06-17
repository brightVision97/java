package Recursion;

import java.util.*;
import java.util.stream.Collectors;

public class Swappings
{
    static String findSequenceAtTheEnd(int[] numbers, int index, List<Integer> sequence)
    {
        if (index >= numbers.length)
            return listToString(sequence);
        else
        {
            swap(sequence.indexOf(numbers[index]), sequence);

            findSequenceAtTheEnd(numbers, ++index, sequence);
        }

        return listToString(sequence).trim();
    }

    static String swap(int indexOfSwapper, List<Integer> sequence)
    {
        int[] temp = new int[indexOfSwapper];

        for (int i = 0; i < temp.length; i++)
            temp[i] = sequence.get(i);

        int end = sequence.size() - 1 - indexOfSwapper;
        int swapper = sequence.get(indexOfSwapper);

        int p = 1;
        for (int i = 0; i < end; i++)
        {
            sequence.set(i, sequence.get(indexOfSwapper + p));
            ++p;
        }

        p = 0;
        sequence.set(end, swapper);
        for (int i = end + 1; i < sequence.size(); i++)
        {
            sequence.set(i, temp[p]);
            ++p;
        }

        return listToString(sequence);
    }

    static String listToString(List<Integer> sequence)
    {
        return sequence.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
    }

    static int[] stringArrToIntArr(String[] input)
    {
        return Arrays.stream(input)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    static List<Integer> addNummbers(int n, List<Integer> list)
    {
        for (int i = 1; i <= n; i++)
            list.add(i);

        return list;
    }

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        input.nextLine();

        List<Integer> sequence = addNummbers(n, new ArrayList<>());

        int[] numbers = stringArrToIntArr(input.nextLine().split("\\s"));

        System.out.println(findSequenceAtTheEnd(numbers, 0, sequence));
    }
}
