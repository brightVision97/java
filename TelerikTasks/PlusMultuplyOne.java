import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class PlusMultuplyOne
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        String[] in = input.nextLine().split(" ");

        int S = Integer.parseInt(in[0]);
        int M = Integer.parseInt(in[1]);

        Queue<Integer> sequence = new LinkedList<>();
        sequence.add(S);

        int num = 0;
        for (int i = 1; i <= M; i++)
        {
            num = sequence.poll();

            sequence.add(num + 1);
            sequence.add((2 * num) + 1);
            sequence.add(num + 2);
        }

        System.out.println(num);
    }
}