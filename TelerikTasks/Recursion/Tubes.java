package recursion;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Marto is a well-known Pernik fighter. He has N tubes with different sizes.
 * Marto also has M-1 friends. His friends also need tubes for fighting.
 * <p>
 * Help Marto to cut his own tubes into exactly M parts with same sizes.
 * This size should be maximal possible (bigger tube = better fighter).
 * <p>
 * Your task is to write a program that finds the biggest possible size of
 * the M tubes which you can cut from the initial Marto’s tubes.
 */
public class Tubes
{
    static void fakeInput()
    {
        String input = "4\n" +
                "11\n" +
                "803\n" +
                "777\n" +
                "444\n" +
                "555";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    static int binarySearchMaxSize(int[] arr, int x, int left, int right)
    {
        int mid = (left + right) / 2;
        
        if (left > right)
            return mid;
        
        int tubes = 0;
        for (int i = 0; i < arr.length; i++)
            tubes += (arr[i] / mid);
        
        return (tubes < x) ? binarySearchMaxSize(arr, x, left, mid - 1) :
                binarySearchMaxSize(arr, x, mid + 1, right);
    }
    
    static int binarySearchMaxSize(int[] arr, int x)
    {
        return binarySearchMaxSize(arr, x, 1, arr[arr.length - 1]);
    }
    
    public static void main(String[] args) throws IOException
    {
        fakeInput();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        int tubes = Integer.parseInt(br.readLine());
        
        int[] sizes = new int[n];
        for (int i = 0; i < n; i++)
            sizes[i] = Integer.parseInt(br.readLine());
        
        Arrays.sort(sizes);
        
        System.out.println(binarySearchMaxSize(sizes, tubes));
    }
}