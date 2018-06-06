import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class NumbersFrom1ToN
{
    static void fakeInput()
    {
        String input = "5";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    static void printNumbersTo(int number)
    {
        for (int i = 1; i <= number; i++)
            System.out.print(i + " ");
    }

    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);

        printNumbersTo(input.nextInt());
    }
}
