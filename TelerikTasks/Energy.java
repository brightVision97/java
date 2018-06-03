import java.util.Scanner;

public class Energy
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        String n = input.nextLine();

        int sumEvens = 0;
        int sumOdds = 0;

        for (int i = 0; i < n.length(); i++)
        {
            int current = n.charAt(i) - '0';

            if (current % 2 == 0)
                sumEvens += current;
            else
                sumOdds += current;
        }

        if (input != null)
            input.close();

        if (sumEvens > sumOdds)
            System.out.println(sumEvens + " energy drinks");
        else if (sumEvens < sumOdds)
            System.out.println(sumOdds + " cups of coffee");
        else
            System.out.println(sumEvens + " of both");

    }
}
