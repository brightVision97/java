import java.util.Scanner;

public class PhoneNumber
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        String[] inputNumbers = input.nextLine().trim().split("\\s");

        int sum = 0;
        for (int i = 0; i < inputNumbers.length; i++)
        {
            int currentNumber = Integer.parseInt(inputNumbers[i]);

            if (currentNumber % 3 == 0 && currentNumber % 7 == 0)
                sum += currentNumber;
        }

        String stringSum = String.valueOf(sum);
        sum = 0;

        for (int i = 0; i < stringSum.length(); i++)
            sum += Character.getNumericValue(stringSum.charAt(i));

        System.out.println(sum);
    }
}