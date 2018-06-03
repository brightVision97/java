import java.io.ByteArrayInputStream;
import java.util.*;

public class ArrayReverse
{
    static void fakeInput()
    {
        String test = "1 2 3 4 5 6 7";

        System.setIn(new ByteArrayInputStream(test.getBytes()));
    }

    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);

        String[] arr = input.nextLine().split(" ");

        for (int i = arr.length - 1; i >= 0; i--)
        {
            if (i == 0)
            {
                System.out.print(arr[i]);
                break;
            }

            System.out.print(arr[i] + ", ");
        }
    }
}
