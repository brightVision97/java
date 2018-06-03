public class PermutationsTo10
{
    private static boolean permTo10(int[] arr)
    {
        int[] to10 = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        outerLoop : for (int i = 0; i < to10.length; i++)
        {
            for (int j = 0; j < to10.length; j++)
            {
                for (int k = 0; k < i; k++)
                    if (arr[i] == arr[k])
                        return false;

                if (arr[i] != to10[j])
                {
                    if (j == to10.length - 1)
                        return false;

                    continue;
                } else
                    continue outerLoop;
            }
        }

        return true;
    }

    public static void main(String[] args)
    {
        int[] arr = new int[] {4, 2, 3, 1, 5, 6, 7, 8, 9, 10};

        System.out.println(permTo10(arr));
    }
}
