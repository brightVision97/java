package recursion;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * As you know girls like to exchange their clothes.
 * You are given N girls with K shirts and L skirts.
 * For easier understanding shirts are indexed with numbers (zero-based)
 * and skirts are indexed with letters. Numbers are always from 0 to K – 1.
 * Letters are any lowercase English letter.
 * Some skirts are the same so letters are not distinct.
 * <p>
 * Your task is to find in how many ways the girls can
 * choose one shirt and one skirt for each one of them
 * <p>
 * Example:
 * <p>
 * Input:
 * 3
 * baca
 * 2
 * Output:
 * 21
 * 0a-1a
 * 0a-1b
 * 0a-1c
 * 0a-2a
 * 0a-2b
 * 0a-2c
 * 0b-1a
 * 0b-1c
 * 0b-2a
 * 0b-2c
 * 0c-1a
 * 0c-1b
 * 0c-2a
 * 0c-2b
 * 1a-2a
 * 1a-2b
 * 1a-2c
 * 1b-2a
 * 1b-2c
 * 1c-2a
 * 1c-2b
 */
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
                        j + 1, skirts, shirts, i + 1, girls, girlIndex + 1);
                
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