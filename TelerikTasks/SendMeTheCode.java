import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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