import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Управителите на квартала на Червената шапчица си падат леко стиснати.
 * Между всичките N дестинации в квартала са пострени само N - 1 пътеки,
 * така че между всеки две места може да се стигне само по един начин.
 * <p>
 * На всяка дестинация има пари (късметчета, изпускани по земята).
 * Червената шапчица е голям тарикат и иска да събере възможно
 * най-много пари, за да може баба и да пазарува.
 * Тя може да започне от където пожелае и да минава само по пътеките,
 * събирайки парите на всяка дестинация. Това, което и пречи да събере
 * всичко е, че ако мине по някоя пътека повече от веднъж, вълка ще я заподозре.
 * <p>
 * Помогнете на Червената шапчица да разбере колко най-много пари би
 * могла да събере без да рискува да бъде изядена.
 */
public class RedRidingHood
{
    private static class Graph
    {
        List<List<Integer>> vertices;
        
        Graph(int v)
        {
            vertices = new ArrayList<>();
            for (int i = 0; i < v + 1; i++)
                vertices.add(new ArrayList<>());
        }
        
        private void addEdge(int x, int y)
        {
            addDirectedEdge(x, y);
            addDirectedEdge(y, x);
        }
        
        private void addDirectedEdge(int from, int to)
        {
            vertices.get(from).add(to);
        }
        
        public void dfs(int x, int prev, int tempMoney)
        {
            tempMoney += coins[x];
            boolean hasNext = false;
            
            for (int i : vertices.get(x))
            {
                if (i != prev)
                {
                    hasNext = true;
                    dfs(i, x, tempMoney);
                }
            }
            
            if (!hasNext)
            {
                if (tempMoney > maxMoney)
                {
                    maxMoney = tempMoney;
                    bestNode = x;
                }
            }
        }
    }
    
    private static void fakeInput()
    {
        String input = "5\n" +
                "4 5 1 3 0\n" +
                "1 2\n" +
                "5 1\n" +
                "4 5\n" +
                "3 2";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    private static int maxMoney;
    private static int bestNode;
    private static int[] coins;
    
    public static void main(String[] args) throws IOException
    {
        fakeInput();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        int v = Integer.parseInt(reader.readLine());
        
        coins = Arrays.stream(reader.readLine().split("\\s"))
                .mapToInt(Integer::parseInt)
                .toArray();
        
        Graph graph = new Graph(v);
        
        for (int i = 0; i < v - 1; i++)
        {
            String[] input = reader.readLine().split("\\s");
            graph.addEdge(Integer.parseInt(input[0]) - 1, Integer.parseInt(input[1]) - 1);
        }
        
        maxMoney = 0;
        bestNode = -1;
        graph.dfs(0, -1, 0);
        maxMoney = 0;
        
        graph.dfs(bestNode, -1, 0);
        
        System.out.println(maxMoney);
    }
}