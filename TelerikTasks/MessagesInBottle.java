import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * In a warm, sunny day Andrew found a bottle near the sea.
 * It was a very special bottle, containing not one, but two messages.
 * The first message contained a big sequence of digits (0-9).
 * “This must be a secret code”, Andrew thought. And he was right.
 * After seeing the second message everything became clearer.
 * The second message said something like this: “A123C11B98”.
 * Another idea struck Andrew: “Hmm may be this is the cipher,
 * used for creating the secret code”. And again he was right.
 * <p>
 * An alphabetical message, containing only capital English letters,
 * is encoded with the given cipher. For every letter in the original
 * message it is replaced by the given sequence of digits in the cipher.
 * <p>
 * For example an original message ABC with a cipher A123C11B98 will be encoded as 1239811.
 * <p>
 * Write a program that for a given secret code from the first bottle
 * message and a given cipher from the second bottle message finds
 * all possible original messages that can be encoded to the given secret code.
 */
public class MessagesInBottle
{
    private static String secretCodeNumbers;
    private static List<String> list = new ArrayList<>();
    private static int count = 0;
    
    private static void solve(int index, StringBuilder sb, Map<String, String> map)
    {
        if (index == secretCodeNumbers.length())
        {
            ++count;
            list.add(sb.toString());
            return;
        }
        
        for (String key : map.keySet())
        {
            String smallChecker = map.get(key);
            String bigChecker = secretCodeNumbers.substring(index);
            
            if (bigChecker.startsWith(smallChecker))
            {
                sb.append(key);
                solve(index + map.get(key).length(), sb, map);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
    
    private static void fakeInput()
    {
        String input = "1122\n" +
                "A1B12C11D2";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    public static void main(String[] args) throws IOException
    {
        fakeInput();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        secretCodeNumbers = br.readLine();
        
        String[] cipher = br.readLine().split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < cipher.length; i += 2)
            map.put(cipher[i], cipher[i + 1]);
        
        solve(0, new StringBuilder(), map);
        
        list.sort(Comparator.naturalOrder());
        
        System.out.println(count);
        list.forEach(System.out::println);
    }
}
