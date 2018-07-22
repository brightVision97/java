import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Your task is to decode a message sent by a friend of yours to help you cheat on the exam.
 * Be aware that the Big Vik is watching and he could corrupt your message.
 * <p>
 * The message arrives as a sequence of digits. You have the key to decode it.
 * <p>
 * The KEY is:
 * <p>
 * - You have to read the input from right to left.
 * - Decode digit on **odd** position: digit * index^2
 * - Decode digit on **even** position: digit^2 * index
 * <p>
 * indices	        3	2	1
 * array	        5	6	9
 * odd/even	      odd even odd
 * result	5 * 32	6 2 * 2	9 * 12
 * The new sequence of digit is generated
 * result = 5 * 32 + 62 * 2 + 9 * 12 = 126
 * <p>
 * If the last digit is 0 your message is corrupted and Big Vik wins the game
 * Write on the console
 * 10
 * Big Vik wins again!
 * <p>
 * If the last digit is not 0 the length of the message is this digit (6 in the example)
 * You have to find the remainder of result % 26 (26 is the number of letters in the alphabet)
 * s = 126 % 26 = 22
 * Start of the message is at s + 1
 * s + 1 = 23
 * The 23rd letter in the alphabet is W
 * The length of the message is 6 => WXYZAB
 * If the last letter ('Z') is reached you should continue from the beginning ('A')
 * Letters are always capital
 */
public class SendMeTheCode
{
    static void fakeInput()
    {
        String input = "569";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);
        
        List<Integer> numbers =
                Arrays.stream(input.nextLine().replace("-", "").split(""))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
        
        Collections.reverse(numbers);
        
        int result = 0;
        for (int i = 0; i < numbers.size(); i++)
        {
            int position = i + 1;
            
            if (position % 2 == 0)
                result += (position * (numbers.get(i) * numbers.get(i)));
            else
                result += ((position * position) * numbers.get(i));
        }
        
        StringBuilder message = new StringBuilder();
        
        if (result % 10 == 0)
        {
            System.out.println(result + "\nBig Vik wins again!");
            System.exit(0);
        } else
        {
            int length = result % 10;
            int s = result % 26;
            
            String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            
            for (int i = s; i < alphabet.length(); i++)
            {
                message.append(alphabet.charAt(i));
                
                if (alphabet.charAt(i) == 'Z')
                    for (int j = 0; j < length - message.length() + 1; j++)
                        message.append(alphabet.charAt(j));
                
                if (message.length() == length)
                    break;
            }
        }
        
        System.out.println(result + "\n" + message.toString());
    }
}