package recursion;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class Passwords
{
    static void fakeInput()
    {
        String input = "2\n" +
                ">\n" +
                "13";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    static int keysPressed;
    static int finalCombination;
    static int counter = 0;

    static void finalCombination(StringBuilder current, char[] directions, int directionIndex)
    {
        if (current.length() < 1)
        {
            for (int i = 0; i <= 9; i++)
            {
                finalCombination(current.append(i), directions, directionIndex);
                current.deleteCharAt(0);
            }
        }

        if (current.length() == keysPressed)
        {
            if (++counter == finalCombination)
            {
                System.out.println(current);
                System.exit(0);
            }

            return;
        }

        char lastChar = current.charAt(current.length() - 1);
        int start;
        if (lastChar == '0')
            start = 10;
        else
            start = lastChar - '0';

        if (directionIndex < directions.length)
        {
            switch (directions[directionIndex])
            {
                case '>':
                    if (lastChar == '0')
                        return;

                    for (int i = start; i <= 9; i++)
                    {
                        if (i == start)
                        {
                            current.append(0);
                            finalCombination(current, directions, directionIndex + 1);
                            current.deleteCharAt(current.length() - 1);
                        } else
                        {
                            current.append(i);
                            finalCombination(current, directions, directionIndex + 1);
                            current.deleteCharAt(current.length() - 1);
                        }
                    }

                    break;
                case '<':
                    for (int i = 1; i < start; i++)
                    {
                        current.append(i);
                        finalCombination(current, directions, directionIndex + 1);
                        current.deleteCharAt(current.length() - 1);
                    }

                    break;
                case '=':
                    current.append(lastChar);
                    finalCombination(current, directions, directionIndex + 1);
                    current.deleteCharAt(current.length() - 1);

                    break;
                default:
                    break;
            }
        }
    }

    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);

        keysPressed = input.nextInt();
        input.nextLine();

        char[] directions = input.nextLine().toCharArray();

        finalCombination = input.nextInt();

        finalCombination(new StringBuilder(), directions, 0);
    }
}
