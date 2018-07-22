import java.io.ByteArrayInputStream;
import java.util.Scanner;

/**
 * An symmetric array is an array, where the elements are equal at indices:
 * <p>
 * 0 and len - 1
 * 1 and len - 2
 * 2 and len - 3
 * 3 and len - 4
 * etc..
 * <p>
 * You are given some arrays of numbers.
 * Check if they are symmetric.
 */
public class Symmetric
{
    static void fakeInput()
    {
        String test = "4\n" +
                "1 2 3 2 1\n" +
                "2 1\n" +
                "1 2 2 1\n" +
                "4";
        
        System.setIn(new ByteArrayInputStream(test.getBytes()));
    }
    
    static boolean isSymmetric(String[] arr)
    {
        int leftIndex = 0;
        int rightIndex = arr.length - 1;
        
        for (int i = 0; i < arr.length / 2; i++)
            if (!arr[leftIndex++].equals(arr[rightIndex--]))
                return false;
        
        return true;
    }
    
    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);
        
        int n = input.nextInt();
        input.nextLine();
        
        for (int i = 0; i < n; i++)
        {
            String[] arr = input.nextLine().split(" ");
            System.out.println(isSymmetric(arr) ? "Yes" : "No");
        }
    }
}