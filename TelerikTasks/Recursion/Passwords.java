package recursion;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

/**
 * Pesho has been gifted with the supernatural ability of echolocation.
 * One day he decided to test himself. He knew that his sister's Facebook
 * password consists of digits only and she enters them using these keys:
 * <p>
 * 1, 2, 3, 4, 5, 6, 7, 8, 9, 0
 * <p>
 * So, he listened carefully just outside of his sister's room.
 * What he obtained from hearing the typing was:
 * <p>
 * The length of the password - N (he heard N key presses)
 * Whether his sister moved her finger left, right or kept it in place in order to press the next key.
 * E.g. if she pressed key 3 and moved her finger left - the next key can only be either 1 or 2.
 * Now, he decided to find the Kth possible password of all
 * possibilities in lexicographically ascending order (where 0 is before 1; 1 is before 2 and so on).
 * <p>
 * It turns out that his intention is to cheat and use a program.
 * Guess what! He can neither use a computer nor write code.
 * That is why you have to do his work for him and if
 * you are good enough you might even pass the exam :P
 * <p>
 * Example:
 * <p>
 * Input:
 * 10 >>>>>>>>> 1
 * Output:
 * 1234567890
 */
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
