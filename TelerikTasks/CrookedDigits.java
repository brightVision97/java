import java.util.Scanner;

public class CrookedDigits
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        String number = input.nextLine();

        int sum = 0;

        for (int i = 0; i < number.length(); i++)
        {
            char currentCh = number.charAt(i);

            if (Character.isDigit(currentCh))
                sum += currentCh - '0';
        }

        while (sum > 9)
        {
            int newSum = 0;

            while (sum > 0)
            {
                newSum += sum % 10;
                sum /= 10;
            }

            sum = newSum;
        }

        System.out.println(sum);
    }
}