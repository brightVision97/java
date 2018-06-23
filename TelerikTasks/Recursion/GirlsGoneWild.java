package recursion;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GirlsGoneWild
{
    static void fakeInput()
    {
        String input = "3\n" +
                "baca\n" +
                "2";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    static boolean[] used;
    static SortedSet<String> combinations = new TreeSet<>();
    static List<Character> usedLetters = new ArrayList<>();

    static void girlsGoneWild(StringBuilder current, int skirtsIndex, char[] skirts,
                              int shirts, int shirtsIndex, int girls, int girlIndex)
    {
        if (girlIndex == girls)
        {
            combinations.add(current.toString());
            return;
        }

        for (int i = shirtsIndex; i < shirts; i++)
        {
            for (int j = 0; j < skirts.length; j++)
            {
                if (used[j])
                    continue;

                used[j] = true;

                girlsGoneWild(current.append("-").append(i).append(skirts[j]),
                        j + 1, skirts, shirts, i + 1, girls,  girlIndex + 1);

                // delete last three symbols
                current.delete(current.length() - 3, current.length());

                used[j] = false;
            }
        }
    }

    static void printResult()
    {
        System.out.println(combinations.size());
        for (String combination : combinations)
            System.out.println(combination);
    }
    
    public static void main(String[] args) throws IOException
    {
        fakeInput();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        int shirts = Integer.parseInt(bf.readLine());
        char[] skirts = bf.readLine().toCharArray();
        int girls = Integer.parseInt(bf.readLine());

        used = new boolean[skirts.length];

        for (int i = 0; i < shirts; i++)
        {
            for (int j = 0; j < skirts.length; j++)
            {
                StringBuilder start = new StringBuilder().append(i).append(skirts[j]);

                if (usedLetters.contains(skirts[j]))
                    continue;

                usedLetters.add(skirts[j]);

                used[j] = true;

                girlsGoneWild(start, j + 1, skirts, shirts,
                        i + 1, girls, 1);

                used[j] = false;
            }

            usedLetters.clear();
        }

        printResult();
    }
}