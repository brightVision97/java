package recursion;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class AandB
{
    static void fakeInput()
    {
        String input = "4\n" +
                "7 5";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    static void permutation(char[] container, int position, String ab)
    {
        if (position == container.length)
            System.out.println(new String(container));
        else
        {
            for (int i = 0; i < ab.length(); i++)
            {
                container[position] = ab.charAt(i);

                permutation(container, position + 1, ab);
            }
        }
    }

    static String sortString(String inputString)
    {
        char tempArray[] = inputString.toCharArray();

        Arrays.sort(tempArray);

        return new String(tempArray);
    }

    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        input.nextLine();
        String ab = sortString(input.nextLine().replace(" ", ""));

        permutation(new char[n], 0, ab);
    }
}