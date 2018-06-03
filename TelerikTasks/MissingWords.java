import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class MissingWords
{
    static void fakeInput()
    {
        String input = "I am Ivan Rachev\n" +
                "Rachev I am I";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);

        String[] s = input.nextLine().split("\\s");
        String[] t = Arrays.stream(input.nextLine().split("\\s"))
                .distinct().toArray(String[]::new);

        int[] indices = new int[t.length];

        int currentIndex = 0;
        for (int i = 0; i < s.length; i++)
            for (int j = 0; j < t.length; j++)
                if (s[i].equals(t[j]))
                    indices[currentIndex++] = i;

        for (int i = 0; i < indices.length; i++)
            s[indices[i]] = null;

        String[] missingWords = new String[s.length - t.length];

        currentIndex = 0;
        for (int i = 0; i < s.length; i++)
            if (s[i] != null)
                missingWords[currentIndex++] = s[i];

        System.out.println(Arrays.toString(missingWords));
    }
}