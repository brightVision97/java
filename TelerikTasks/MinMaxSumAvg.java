import java.io.ByteArrayInputStream;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.Scanner;

/**
 * Write a program that reads from the console a sequence of N
 * real numbers and returns the minimal, the maximal number,
 * the sum and the average of all numbers
 * (displayed with 2 digits after the decimal point).
 * <p>
 * The input starts by the number N (alone in a line)
 * followed by N lines, each holding an real number.
 */
public class MinMaxSumAvg
{
    static void fakeInput()
    {
        String input = "3\n" +
                "2\n" +
                "5\n" +
                "1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);
        
        int n = input.nextInt();
    
        double[] numbers = new double[n];
        
        for (int i = 0; i < n; i++)
            numbers[i] = input.nextDouble();
        
        DoubleSummaryStatistics stats = Arrays.stream(numbers).summaryStatistics();
        DecimalFormat df = new DecimalFormat("#.00");
        
        System.out.println("min=" + df.format(stats.getMin()));
        System.out.println("max=" + df.format(stats.getMax()));
        System.out.println("sum=" + df.format(stats.getSum()));
        System.out.println("avg=" + df.format(stats.getAverage()));
        
    }
}
