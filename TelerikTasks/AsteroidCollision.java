import java.util.Arrays;
import java.util.Stack;

public class AsteroidCollision
{
    public static int[] asteroidCollision(int[] asteroids)
    {
        Stack<Integer> stack = new Stack();
        
        asteroids:
        for (int asteroid : asteroids)
        {
            while (!stack.isEmpty() && asteroid < 0 && 0 < stack.peek())
                if (-asteroid < stack.peek() || -asteroid == stack.pop())
                    continue asteroids;
            
            stack.push(asteroid);
        }
        
        return stack.stream().mapToInt(i -> i).toArray();
    }
    
    public static void main(String[] args)
    {
        System.out.println(Arrays.toString(asteroidCollision(new int[]{1, 2, 3, -2})));
    }
}