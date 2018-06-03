import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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