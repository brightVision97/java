import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A long time ago, in a galaxy far, far away...
 * <p>
 * All jedi must mediate. Yet, when the Jedi are at their temple,
 * they cannot mediate at the same time, because the
 * Force will become too much at one place and implosion will occur.
 * There is a strict order for meditations: Jedi Masters mediate first,
 * then Jedi Knights, and last are the padawans.
 * <p>
 * Given the sequence of Jedi:
 * <p>
 * P, K, M, M, K, P
 * they will mediate in the following order:
 * <p>
 * M, M, K, K, P, P
 * given that M means a Jedi Master, K is a Jedi Knight, and P is a padawan.
 * <p>
 * Joro and Gosho are padawans. They want to have as much time with the
 * Force as they can. Given that, they do not want to wait meaninglessly for mediation.
 * That is why, you can help them to solve in which order all Jedi will mediate.
 * <p>
 * Example:
 * <p>
 * Input:
 * 3
 * M1 K1 P1
 * Output:
 * M1 K1 P1
 */
public class JediMeditation
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        
        int n = input.nextInt();
        
        input.nextLine();
        String unordered = input.nextLine().trim();
        
        Matcher masters = Pattern.compile("M\\d+").matcher(unordered);
        Matcher knights = Pattern.compile("K\\d+").matcher(unordered);
        Matcher padawans = Pattern.compile("P\\d+").matcher(unordered);
        
        StringBuilder ordered = new StringBuilder();
        
        while (masters.find())
            ordered.append(masters.group()).append(" ");
        while (knights.find())
            ordered.append(knights.group()).append(" ");
        while (padawans.find())
            ordered.append(padawans.group()).append(" ");
        
        System.out.println(ordered.toString());
    }
}