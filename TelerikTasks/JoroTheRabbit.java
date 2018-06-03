import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class JoroTheRabbit
{
    static void fakeInput()
    {
        String input = "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args)
    {
        fakeInput();
        Scanner in = new Scanner(System.in);

        Integer[] field = Arrays.stream(in.nextLine().split(",+\\s"))
                .map(Integer::parseInt).toArray(Integer[]::new);

        int currentRun = 1;
        int longestRun = currentRun;

        for (int index = 0; index < field.length; index++)
        {
            for (int jump = 1; jump < field.length; jump++)
            {
                currentRun = 1;

                int currentIndex = index;
                int nextIndex = currentIndex + jump;

                if (nextIndex < 0)
                    nextIndex += field.length;

                if (nextIndex > field.length - 1)
                    nextIndex -= field.length;

                while (field[nextIndex] > field[currentIndex] && (nextIndex != index))
                {
                    currentRun++;

                    currentIndex = nextIndex;
                    nextIndex = currentIndex + jump;

                    if (nextIndex < 0)
                        nextIndex += field.length;

                    if (nextIndex > field.length - 1)
                        nextIndex -= field.length;
                }

                longestRun = Math.max(longestRun, currentRun);
            }
        }

        longestRun = Math.max(longestRun, currentRun);
        System.out.println(longestRun);
    }
}
