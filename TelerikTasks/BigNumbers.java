import java.util.Scanner;

/**
 * Write a method that adds two positive integer numbers
 * represented as arrays of digits (each array element arr[i]
 * contains a digit; the last digit is kept in arr[0]).
 * <p>
 * Write a program that reads two arrays representing
 * positive integers and outputs their sum.
 */
public class BigNumbers
{
    private static String sum(String num1, String num2)
    {
        StringBuilder result = new StringBuilder();
        
        byte carry = 0;
        for (int i = 0; i < Math.max(num1.length(), num2.length()); i++)
        {
            byte currentDigit =
                    (byte) ((i < num1.length() ? Character.getNumericValue(num1.charAt(i)) : 0) +
                            (i < num2.length() ? Character.getNumericValue(num2.charAt(i)) : 0) + carry);
            
            carry = (byte) (currentDigit / 10);
            result.append((byte) (currentDigit % 10) + " ");
        }
        if (carry == 1)
            result.append((byte) 1);
        
        return result.toString();
    }
    
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        
        input.nextLine();
        String firstNum = input.nextLine().replaceAll("\\s", "");
        String secondNum = input.nextLine().replaceAll("\\s", "");
        
        System.out.println(sum(firstNum, secondNum));
    }
}