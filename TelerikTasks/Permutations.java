import java.util.Scanner;

/**
 * By given N, generate all permutations with numbers between 1 and N.
 */
public class Permutations
{
    public static void swap(int[] arr, int i, int j)
    {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
    public static boolean permuteLexically(int[] arr)
    {
        if (arr.length == 1)
            return false;
        
        int i = arr.length - 2;
        while (arr[i] >= arr[i + 1])
        {
            i--;
            if (i < 0)
                return false;
        }
        
        int j = arr.length - 1;
        while (arr[i] >= arr[j])
            j--;
        
        swap(arr, i, j);
        
        int length = arr.length - (i + 1);
        for (int k = 0; k < length / 2; k++)
            swap(arr, i + 1 + k, arr.length - k - 1);
        
        return true;
    }
    
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        
        int n = input.nextInt();
        
        int[] arr = new int[n];
        
        for (int i = 0; i < n; i++)
            arr[i] = i + 1;
        
        do
        {
            for (int i = 0; i < n; i++)
                System.out.print(arr[i] + " ");
            System.out.println();
        } while (permuteLexically(arr));
        
        if (input != null)
            input.close();
    }
}