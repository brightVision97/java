import java.util.Scanner;

public class BalancedNumbers
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        int sum = 0;
        while (true)
        {
            String num = input.nextLine();

            int digit1 = num.charAt(0) - '0';
            int digit2 = num.charAt(1) - '0';
            int digit3 = num.charAt(2) - '0';

            if (digit1 + digit3 == digit2)
            {
                sum += Integer.parseInt(num);
                continue;
            }
            else
                break;
        }

        System.out.println(sum);
    }
}