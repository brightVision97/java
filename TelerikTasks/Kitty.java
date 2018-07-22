import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The Kitty is a very dangerous and strange creature. She loves to eat but
 * also loves to collect souls. Not ordinary souls but the souls of coders.
 * In order to not collect your soul Kitty assigned you a task to find how
 * much food and souls she could collect in any iteration. Kitty doesn't know
 * she can be deadlocked and the rest of the coders will be saved. There is a catch.
 * Kitty could escape a deadlock if she leaves a soul or food in that cell.
 * <p>
 * Your task is to calculate the food and the souls
 * collected by Kitty or to output that she is deadlocked.
 * <p>
 * On the first line of the input you will receive the positions of the
 * coder souls ("@"), food ("*") and deadlocks ("x") as string.
 * <p>
 * On the second line of the input you will receive the path of the Kitty
 * as string with integers separated by single space.
 * Positive means, move to the right, negative means, move to the left.
 * <p>
 * The starting position will always be at index 0.
 * <p>
 * The final result is either the souls, food and deadlocks count
 * or a string informing that the Kitty is deadlocked.
 * The format is shown in the zero tests and the example.
 *
 * @ - symbol for coder soul
 * * - symbol for food
 * x - symbol for deadlock
 * <p>
 * Example:
 * <p>
 * -> @@@xx@
 * 1 -1 -1 4
 * <p>
 * We start at position 0 and collect a soul @
 * We move 1 time to the right and now we are at position 1 and collect a soul @
 * We move 1 time to the left and now we are at position 0 and nothing is
 * there because we already collected it, so we continue our path.
 * We move 1 time to the left and now we are at position 6 (if you exit
 * the array you should enter from the other side) and we collect food *
 * We move 4 times to the right and now we are at position 3 and there is a deadlock.
 * If the deadlock is on even position the Kitty should leave a soul (@) there in order to escape.
 * If the deadlock is on odd position the Kitty should leave food (*) there in order to escape.
 * Position 3 is odd so we leave food (*) to escape the deadlock.
 * At the end we have collected 2 souls and 1 food and we have left 1 food to escape a deadlock.
 * <p>
 * So the final result is:
 * <p>
 * Coder souls collected: 2
 * Food collected: 0
 * Deadlocks: 1
 */
public class Kitty
{
    static void fakeInput()
    {
        String input = "@*@*@*xxx\n" +
                "1 -1 1 -1 2 1 1 1 1 1 1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    public static void main(String[] args)
    {
        fakeInput();
        Scanner sc = new Scanner(System.in);
        
        char[] input = sc.nextLine().toCharArray();
        
        int[] path = Arrays.stream(sc.nextLine().split("\\s"))
                .mapToInt(Integer::parseInt)
                .toArray();
        
        boolean isKittyFuckedUp = false;
        int length = path.length;
        int inputLength = input.length;
        
        int souls = 0;
        int deadlocks = 0;
        int food = 0;
        int countJumps = 0;
        
        int indexer = 0;
        
        if (input[indexer] == '@')
        {
            souls++;
            input[indexer] = '0';
        } else if (input[indexer] == '*')
        {
            food++;
            input[indexer] = '0';
        } else if (input[indexer] == 'x')
            isKittyFuckedUp = true;
        
        if (!isKittyFuckedUp)
        {
            for (int i = 0; i < length; i++)
            {
                countJumps++;
                
                int nextPossition = indexer;
                nextPossition += path[i];
                int direction = path[i];
                
                if (nextPossition < 0)
                {
                    while (nextPossition < 0)
                        nextPossition += inputLength;
                    
                    indexer = nextPossition;
                    
                } else if (nextPossition > inputLength - 1)
                {
                    while (nextPossition > inputLength - 1)
                        nextPossition -= inputLength;
                    
                    indexer = nextPossition;
                } else
                    indexer += direction;
                
                if (input[indexer] == '@')
                {
                    souls++;
                    input[indexer] = '0';
                    
                } else if (input[indexer] == '*')
                {
                    food++;
                    input[indexer] = '0';
                } else if (input[indexer] == 'x')
                {
                    if (indexer % 2 == 0)
                    {
                        if (souls > 0)
                        {
                            deadlocks++;
                            souls--;
                            input[indexer] = '@';
                        } else
                        {
                            isKittyFuckedUp = true;
                            break;
                        }
                    } else
                    {
                        if (food > 0)
                        {
                            deadlocks++;
                            food--;
                            input[indexer] = '*';
                        } else
                        {
                            isKittyFuckedUp = true;
                            break;
                        }
                    }
                }
            }
        }
        
        if (isKittyFuckedUp)
            System.out.println("You are deadlocked, you greedy kitty!" + "\n" +
                    "Jumps before deadlock: " + countJumps);
        else
            System.out.println("Coder souls collected: " + souls + "\n" +
                    "Food collected: " + food + "\n" +
                    "Deadlocks: " + deadlocks);
    }
}