import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * We are given the following sequence:
 * <p>
 * S1 = N;
 * S2 = S1 + 1;
 * S3 = 2*S1 + 1;
 * S4 = S1 + 2;
 * S5 = S2 + 1;
 * S6 = 2*S2 + 1;
 * S7 = S2 + 2;
 * <p>
 * Your task is to write a program, that by given N and M, finds the Mth member of the sequence.
 */
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