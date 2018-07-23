import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * When you first learned to program, you were learned how to swap numbers.
 * When Steve learned to program, he was able to swap a whole bunch of them.
 * <p>
 * When Steve raises his hands and says a number X all the numbers left and right of X swap their places.
 * <p>
 * Example:
 * <p>
 * [a b c] X [d e f]
 * will become:
 * [d e f] X [a b c]
 * <p>
 * You are given the sequence 1, 2, 3, ... N. Steve will say some numbers.
 * Your task is to write a program that finds the sequence at the end.
 */
public class Swappings
{
    private static class Link
    {
        private int value;
        private Link left;
        private Link right;
        
        Link(Link end, int value)
        {
            this.value = value;
            left = end;
            right = null;
            
            if (end != null)
                end.right = this;
        }
        
        private static void detach(Link link)
        {
            if (link.left != null)
                link.left.right = null;
            if (link.right != null)
                link.right.left = null;
            
            link.left = null;
            link.right = null;
        }
        
        private static void attach(Link left, Link right)
        {
            if (left.equals(right))
                return;
            
            left.right = right;
            right.left = left;
        }
    }
    
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        
        int n = input.nextInt();
        input.nextLine();
        
        List<Integer> swaps = Arrays.stream(input.nextLine().split("\\s"))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        
        Link[] links = new Link[n + 1];
        for (int i = 1; i <= n; ++i)
            links[i] = new Link(links[i - 1], i);
        
        Link leftEnd = links[1];
        Link rightEnd = links[n];
        
        for (int i : swaps)
        {
            Link mid = links[i];
            Link newRight = mid.left;
            Link newLeft = mid.right;
            
            Link.detach(mid);
            Link.attach(rightEnd, mid);
            Link.attach(mid, leftEnd);
            
            leftEnd = newLeft != null ? newLeft : mid;
            rightEnd = newRight != null ? newRight : mid;
        }
        
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
        {
            nums[i] = leftEnd.value;
            leftEnd = leftEnd.right;
        }
        
        Arrays.stream(nums).forEach(num -> System.out.print(num + " "));
    }
}
