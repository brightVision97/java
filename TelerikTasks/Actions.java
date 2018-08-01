import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * И на готвачите не им е лесно. Те имат множество действия
 * (палене на котлон, забъркване на смес), които трябва да извършват в правилния ред.
 * <p>
 * Има N действия. Всяко е зададено като различно цяло число в интервала [0, N).
 * Предпочитания ред за извършване на действията, разбира се, е 0, 1, 2, 3, ...
 * Обаче някои действия трябва задължително да са преди други (например
 * подготвянето на съд преди изливане на смес в него).
 * <p>
 * По зададени M на брой задължителни подредби, намерете ред,
 * в който всички действия спазват първо задължителния, а после предпочитания ред.
 */
public class Actions
{
    private static void order(StringBuilder result, Map<Integer, List<Integer>> edges)
    {
        if (edges.isEmpty())
            return;
        
        for (Integer key : edges.keySet())
        {
            if (!edges.get(key).isEmpty())
                continue;
            
            result.append(key).
                    append("\n");
            edges.remove(key);
            clear(edges, key);
            order(result, edges);
            break;
        }
    }
    
    private static void clear(Map<Integer, List<Integer>> edges, Integer key)
    {
        for (Integer i : edges.keySet())
            edges.get(i).remove(key);
    }
    
    private static void fakeInput()
    {
        String input = "5 5\n" +
                "0 3\n" +
                "2 1\n" +
                "1 4\n" +
                "1 3\n" +
                "4 3";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    public static void main(String[] args) throws IOException
    {
        fakeInput();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        String[] input = reader.readLine().split("\\s");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        
        Map<Integer, List<Integer>> edges = new HashMap<>();
        
        for (int i = 0; i < n; i++)
            edges.put(i, new ArrayList<>());
        
        for (int i = 0; i < m; i++)
        {
            String[] pair = reader.readLine().split("\\s");
            int a = Integer.parseInt(pair[0]);
            int b = Integer.parseInt(pair[1]);
            
            edges.get(b).add(a);
        }
        
        StringBuilder output = new StringBuilder();
        order(output, edges);
        
        System.out.println(output.toString());
    }
}