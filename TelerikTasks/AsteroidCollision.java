import java.util.Arrays;
import java.util.Stack;

/**
 * We are given an array asteroids of integers representing asteroids in a row.
 * <p>
 * For each asteroid, the absolute value represents its size, and the sign represents
 * its direction (positive meaning right, negative meaning left).
 * Each asteroid moves at the same speed.
 * <p>
 * Find out the state of the asteroids after all collisions.
 * If two asteroids meet, the smaller one will explode.
 * If both are the same size, both will explode.
 * Two asteroids moving in the same direction will never meet.
 * <p>
 * Example:
 * <p>
 * Input:
 * asteroids = [5, 10, -5]
 * Output: [5, 10]
 * Explanation:
 * The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
 */
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