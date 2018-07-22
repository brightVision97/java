package recursion;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Pesho thinks that numerology is buggy. That is why he developed his own
 * method of playing with the divine, suitable for programmers.
 * <p>
 * Here is how to calculate your abilities.
 * <p>
 * You start with a number (always a number) - your birthday in the format YYYYMMDD.
 * Instead of summing the digits, do the following operations:
 * <p>
 * Choose two neighbouring digits - a and b
 * Perform the following operation: (a + b) * (a ^ b) % 10
 * + is addition
 * * is multiplication
 * ^ is bitwise exclusive or (XOR)
 * % is modulo division
 * % 10 means get just the last digit
 * Replace the chosen digits with the result
 * You get an one-digit shorter number
 * Repeat the process until you get only a single digit
 * Pesho noticed that depending on your choices of digits you would get
 * different results in the end. So he advises you to do each possible
 * combination and count how many times you get 0, 1, ... 9.
 * <p>
 * After you've done all the calculations you can look yourself up in the table:
 * <p>
 * Digit	Meaning
 * 0	    You are a very good programmer
 * 1	    You will create very useful programs
 * 2    	You are ugly
 * 3	    You will always depend on an IDE
 * 4	    You like strange languages
 * 5	    You loath science fiction
 * 6	    6? 6 is still a mystery to Pesho
 * 7	    You think class inheritance is magic
 * 8	    You do not mind dynamic typing
 * 9	    You are going to be famous
 * <p>
 * Example:
 * <p>
 * Input:
 * 18790314
 * Output:
 * 0 1006 0 286 0 1473 0 205 0 2070
 */
public class Numerology
{
    static void fakeInput()
    {
        String input = "19970328";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    static void count(List<Integer> digits, int[] digitsCounter)
    {
        if (digits.size() == 1)
        {
            digitsCounter[digits.get(0)]++;
            return;
        }
        
        for (int i = 1; i < digits.size(); i++)
        {
            int digitA = digits.get(i - 1);
            int digitB = digits.get(i);
            
            digits.set(i - 1, calculateDigit(digitA, digitB));
            digits.remove(i);
            
            count(digits, digitsCounter);
            
            digits.set(i - 1, digitA);
            digits.add(i, digitB);
        }
    }
    
    static int calculateDigit(int a, int b)
    {
        return ((a + b) * (a ^ b)) % 10;
    }
    
    static void print(int[] digitsCounter)
    {
        Arrays.stream(digitsCounter)
                .forEach(x -> System.out.print(x + " "));
    }
    
    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);
        
        List<Integer> digits = Arrays.stream(input.nextLine().split(""))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        
        int[] digitsCounter = new int[10];
        
        count(digits, digitsCounter);
        
        print(digitsCounter);
    }
}