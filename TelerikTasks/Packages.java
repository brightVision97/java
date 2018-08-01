import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Imagine you are a package manager. You have a list of
 * dependencies and a user asks you to install some package.
 * <p>
 * Your task is to list the dependencies of a package when asked for a package.
 * <p>
 * Input:
 * Read from the standard input
 * On the first, there will be the number M
 * The number of dependencies
 * On the next M lines, there will be a couple of numbers
 * P and D
 * P depends on D
 * On the M+2th line there will the number K
 * The number of packages, you are asked to install
 * On the next K lines find a single number
 * The package to install
 */
public class Packages
{
    private static void fakeInput()
    {
        String input = "6\n" +
                "4 8\n" +
                "5 3\n" +
                "2 1\n" +
                "5 1\n" +
                "5 8\n" +
                "3 2\n" +
                "4\n" +
                "5\n" +
                "2\n" +
                "8\n" +
                "1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    private static List<Integer> visited;
    private static Map<Integer, List<Integer>> graph;
    private static SortedSet<Integer> sorted;
    
    public static void main(String[] args) throws IOException
    {
        fakeInput();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(reader.readLine());
        
        graph = new HashMap<>();
        visited = new ArrayList<>();
        sorted = new TreeSet<>(Comparator.naturalOrder());
        
        for (int i = 0; i < n; i++)
        {
            int[] pd = Arrays.stream(reader.readLine().split("\\s"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            
            if (!graph.containsKey(pd[0]))
                graph.put(pd[0], new ArrayList<>());
            
            graph.get(pd[0]).add(pd[1]);
        }
        
        int packages = Integer.parseInt(reader.readLine());
        for (int i = 0; i < packages; i++)
        {
            dfs(Integer.parseInt(reader.readLine()));
            
            System.out.println(
                    sorted.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining(" ")));
            
            visited.clear();
            sorted.clear();
        }
    }
    
    private static void dfs(int packageToInstall)
    {
        List<Integer> currentVertex = graph.get(packageToInstall);
        
        visited.add(packageToInstall);
        
        if (currentVertex == null)
        {
            sorted.add(packageToInstall);
            return;
        }
        
        for (Integer vertex : currentVertex)
        {
            if (visited.contains(vertex))
                continue;
            
            dfs(vertex);
        }
        
        sorted.add(packageToInstall);
    }
}