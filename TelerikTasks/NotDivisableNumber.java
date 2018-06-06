import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class NotDivisableNumber
{
    static void fakeInput()
    {
        String input = "10";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);

        int number = input.nextInt();

        for (int i = 1; i <= number; i++)
        {
            if (i % 3 == 0 || i % 7 == 0)
                continue;
            else
                System.out.print(i + " ");
        }
    }
}
