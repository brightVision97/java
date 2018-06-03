import java.util.Scanner;

public class NextPermutation
{
    public static int[] nextPermutation(int[] array)
    {
        int i = array.length - 1;
        while (i > 0 && array[i - 1] >= array[i])
            i--;

        if (i <= 0)
            return null;

        int j = array.length - 1;
        while (array[j] <= array[i - 1])
            j--;

        int temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;

        j = array.length - 1;
        while (i < j)
        {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }

        return array;
    }

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        int[] prevPermArr = new int[n];

        for (int i = 0; i < prevPermArr.length; i++)
            prevPermArr[i] = input.nextInt();

        if (input != null)
            input.close();

        prevPermArr = nextPermutation(prevPermArr);

        for (int i = 0; i < prevPermArr.length; i++)
        {
            if (i != prevPermArr.length - 1)
                System.out.print(prevPermArr[i] + " ");
            else
                System.out.print(prevPermArr[i]);
        }
    }
}
