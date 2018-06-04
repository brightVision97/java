import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class NumbersTriangle
{
    static void fakeInput()
    {
        String input = "5";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    static void printTriangle(int n)
    {
        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= i; j++)
                System.out.print(j + " ");
            System.out.println();
        }

        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= n - i; j++)
                System.out.print(j + " ");

            if (i != n)
                System.out.println();
        }
    }

    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);

        printTriangle(input.nextInt());
    }
}
