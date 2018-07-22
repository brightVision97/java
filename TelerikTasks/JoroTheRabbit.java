import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Joro is a rabbit. But he is no ordinary rabbit – he just loves to jump around.
 * But jumping around without any precalculated direction is too ordinary,
 * so he likes jumping in just a given direction
 * and to make it more fun, the jumping is done in a circle.
 * By given terrain, help Joro find longest fun and not ordinary route of jumps.
 * You are given the terrain as sequence of numbers. The terrain should form a circle,
 * so the last number is before the first, and the first is after the last.
 * <p>
 * Joro can enter the terrain from every position,
 * jump only on numbers larger than the one he is on,
 * only in direction left-to-right and with the same step.
 * Joro’s jumping steps range from 1 to the size of the terrain.
 * Joro cannot jump on position that he already visited.
 */
public class JoroTheRabbit
{
    static void fakeInput()
    {
        String input = "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    public static void main(String[] args)
    {
        fakeInput();
        Scanner in = new Scanner(System.in);
        
        Integer[] field = Arrays.stream(in.nextLine().split(",+\\s"))
                .map(Integer::parseInt).toArray(Integer[]::new);
        
        int currentRun = 1;
        int longestRun = currentRun;
        
        for (int index = 0; index < field.length; index++)
        {
            for (int jump = 1; jump < field.length; jump++)
            {
                currentRun = 1;
                
                int currentIndex = index;
                int nextIndex = currentIndex + jump;
                
                if (nextIndex < 0)
                    nextIndex += field.length;
                
                if (nextIndex > field.length - 1)
                    nextIndex -= field.length;
                
                while (field[nextIndex] > field[currentIndex] && (nextIndex != index))
                {
                    currentRun++;
                    
                    currentIndex = nextIndex;
                    nextIndex = currentIndex + jump;
                    
                    if (nextIndex < 0)
                        nextIndex += field.length;
                    
                    if (nextIndex > field.length - 1)
                        nextIndex -= field.length;
                }
                
                longestRun = Math.max(longestRun, currentRun);
            }
        }
        
        longestRun = Math.max(longestRun, currentRun);
        System.out.println(longestRun);
    }
}
