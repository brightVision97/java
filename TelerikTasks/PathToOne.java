import java.util.Scanner;

public class PathToOne
{
    public static int shortestPath(int n)
    {
        int counter = 0;

        while (n > 1)
        {
            if (n % 2 == 0)
            {
                n /= 2;
                counter++;
            }
            else if (n % 4 == 1 || n == 3)
            {
                counter += 2;
                n = --n / 2;
            }
            else if (n % 4 == 3)
            {
                counter += 2;
                n = ++n / 2;
            }
        }

        return counter;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        if (input != null)
            input.close();

        System.out.println(shortestPath(n));
    }
}
