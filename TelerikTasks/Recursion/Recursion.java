package recursion;

import java.math.BigInteger;

public class Recursion
{
    static BigInteger factorial(BigInteger n)
    {
        if (n.intValue() == 1 || n.intValue() == 0)
            return BigInteger.ONE;
        
        return n.multiply(factorial(n.subtract(BigInteger.ONE)));
    }
    
    static int fibonacci(int n)
    {
        int[] fib = new int[n];
        fib[0] = 0;
        fib[1] = 1;
        fib[2] = 1;
        
        for (int i = 3; i < n; i++)
            fib[i] = fib[i - 1] + fib[i - 2];
        
        return fib[n - 1];
    }
    
    static boolean isPalindrome(String word)
    {
        if (word.equals(reverse(word)))
            return true;
        
        return false;
    }
    
    static String reverse(String str)
    {
        StringBuilder reversed = new StringBuilder();
        
        if (str.length() == 1)
            return str;
        
        return reversed.append(reverse(str.substring(1)) +
                str.charAt(0)).toString();
    }
    
    static void printArray(int[] array)
    {
        for (int i = 0; i < array.length; i++)
            System.out.print(array[i] + " ");
        System.out.println();
    }
    
    static void swap(int[] array, int startIndex, int currentIndex)
    {
        int temp = array[startIndex];
        array[startIndex] = array[currentIndex];
        array[currentIndex] = temp;
    }
    
    static void permuteArray(int[] array, int startIndex)
    {
        if (startIndex == array.length - 1)
        {
            printArray(array);
            return;
        }
        
        for (int i = startIndex; i < array.length; i++)
        {
            swap(array, startIndex, i);
            
            permuteArray(array, startIndex + 1);
            
            swap(array, startIndex, i);
        }
    }
    
    public static void main(String[] args)
    {
        System.out.println(factorial(BigInteger.valueOf(5)));
        
        System.out.println(reverse("Telerik"));
        
        System.out.println(fibonacci(15));
        
        System.out.println(isPalindrome("rotor"));
        
        permuteArray(new int[]{2, 4, 7, 1}, 0);
    }
}
