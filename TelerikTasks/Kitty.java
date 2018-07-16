import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;

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