import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Indices
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        input.nextLine();

        Integer[] indices =
                Arrays.stream(input.nextLine().trim().split("\\s"))
                .map(Integer::parseInt).toArray(Integer[]::new);

        boolean[] visited = new boolean[indices.length];

        List<String> sequence = new ArrayList<>();
        sequence.add("0");
        visited[0] = true;

        int index = indices[0];
        while (true)
        {
            if (index < 0 || index >= indices.length)
            {
                System.out.println(String.join(" ", sequence));
                break;
            }

            if (visited[index])
            {
                sequence.add(sequence.indexOf(String.valueOf(index)), "(");
                sequence.add(")");

                System.out.println(String.join(" ", sequence)
                        .replace(" ( ", "(")
                        .replace(" )", ")")
                        .replace("( ", "("));
                break;
            }

            visited[index] = true;

            sequence.add(String.valueOf(index));

            index = indices[index];
        }
    }
}