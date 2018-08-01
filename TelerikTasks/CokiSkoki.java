import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * Coki likes to make skok-podskok.
 * <p>
 * Recently Koci, his evil genius twin, used this weakness of Coki and started making experiments on him.
 * The last experiment was successful and now Coki gained the superpower to make really big skoks (jumps).
 * Now, Coki is known as the masked vigilante The Big Skok, with the cape and underwear-above-the-pants stuff.
 * <p>
 * He wants to try his newfound powers. He is in the city, and there are really high buildings there.
 * He wants to try to make the longest possible sequence of jumps.
 * Since Coki is still pretty new at this, he can jump only on buildings that are
 * higher than the one he is on, and also there are not higher buildings between.
 * Also, Coki can jump only from left to right.
 * <p>
 * Help Coki to hone his powers!
 * <p>
 * The heights of the buildings are represented as a sequence of numbers.
 * You task is to find the longest sequence of jumps that Coki can make, starting from each building.
 */
public class CokiSkoki
{
    static void fakeInput()
    {
        String input = "6\n" +
                "1 4 2 6 3 4";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);
        
        int[] hights = new int[input.nextInt()];
        for (int i = 0; i < hights.length; i++)
            hights[i] = input.nextInt();
        
        int[] result = new int[hights.length];
        
        Stack<Integer> stack = new Stack<>();
        
        for (int i = hights.length - 1; i >= 0; i--)
        {
            while (!stack.empty() && hights[stack.peek()] <= hights[i])
                result[stack.pop()] = stack.size();
            
            stack.push(i);
        }
        
        while (!stack.empty())
            result[stack.pop()] = stack.size();
        
        System.out.println(Arrays.stream(result).max().getAsInt());
        Arrays.stream(result).forEach(el -> System.out.print(el + " "));
    }
}