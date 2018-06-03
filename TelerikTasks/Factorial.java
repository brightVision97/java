import java.math.BigDecimal;
import java.util.Scanner;

public class Factorial
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        if (n >= 0 && n <= 100)
        {
            BigDecimal sum = BigDecimal.ONE;

            for (int i = 2; i <= n; i++)
                sum = sum.multiply(BigDecimal.valueOf(i));

            System.out.println(sum);
        }
    }
}
