import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Pesho hates ordinary walking. This is so not because he is lazy,
 * but he much more likes jumping and running.
 * One day in Pesho's village an unexpected snow started to rain and caught
 * Pesho unprepared – he was not wearing the appropriate clothes and shoes.
 * But Pesho hated ordinary walking so much, that he continued jumping, instead of walking.
 * While jumping the way home, Pesho slipped, fell down and hurt his leg.
 * Then Pesho's friends in need started wondering: "Which hospital is closest to
 * our homes, so when we take Pesho there, we will walk the minimal distance to our homes?"
 * Your task is to help Pesho's friends, before he falls down again!
 * <p>
 * As input data you will receive the homes of Pesho's friends, the hospitals
 * and the distances between them. Your task is to find the hospital that is
 * closest to the friends' homes. A distance from hospital to homes is the sum
 * of the distances from the hospital to each of the friends' homes.
 * You should find the smallest distance.
 * <p>
 * Input
 * Read from the standard input
 * <p>
 * On the first line will be read three integer numbers N, M and H
 * (separated by a single white space), that represent corresponding
 * the number of points on the map (both hospitals and homes),
 * the number of streets between points and the number of hospitals.
 * <p>
 * On the second line will be H integer numbers, the points on the map that are hospitals.
 * All the rest of the points are homes.
 * <p>
 * On the next M lines will be the streets. Each line contains three positive
 * integer numbers F, S and D. That means that there is a street between points
 * F and S, and the distance between them is D. All the streets are directional,
 * i.e. if a street from A to B exists, there is a street from B to A.
 * <p>
 * The input data will be always valid in the format described.
 * <p>
 * Output
 * Print to the standard output
 * <p>
 * As output data you should write a single positive integer number – the minimal possible distance from one hospital to each of the Pesho's friends' homes.
 */
public class FriendsInNeed
{
    private static class Vertex implements Comparable<Vertex>
    {
        private int point;
        private int weight;
        private boolean isHospital;
        
        Vertex(int point, int weight)
        {
            this.point = point;
            this.weight = weight;
            isHospital = false;
        }
        
        Vertex(int point, int weight, boolean isHospital)
        {
            this(point, weight);
            this.isHospital = isHospital;
        }
        
        @Override
        public int compareTo(Vertex other)
        {
            return Integer.compare(weight, other.weight);
        }
    }
    
    private static void addEdge(int[] input, Map<Integer, List<Vertex>> graph,
                                Set<Integer> hospitals)
    {
        addDirectedEdge(input[0], input[1], input[2], graph, hospitals);
        addDirectedEdge(input[1], input[0], input[2], graph, hospitals);
    }
    
    private static void addDirectedEdge(int from, int to, int weight,
                                        Map<Integer, List<Vertex>> graph,
                                        Set<Integer> hospitals)
    {
        if (!graph.containsKey(from))
            graph.put(from, new ArrayList<>());
        
        if (hospitals.contains(from))
            graph.get(from).add(new Vertex(to, weight, true));
        else
            graph.get(from).add(new Vertex(to, weight));
    }
    
    public static Map<Integer, Integer> dijkstra(int vertex, Map<Integer, List<Vertex>> graph)
    {
        Set<Integer> used = new HashSet<>();
        Queue<Vertex> queue = new PriorityQueue<>();
        Map<Integer, Integer> distances = new HashMap<>();
        
        queue.add(new Vertex(vertex, 0));
        distances.put(vertex, 0);
        
        while (!queue.isEmpty())
        {
            Vertex current = queue.poll();
            if (used.contains(current.point))
                continue;
            
            used.add(current.point);
            
            for (Vertex neighbour : graph.get(current.point))
            {
                if (used.contains(neighbour.point))
                    continue;
                
                int newDistanceToNeighbour = distances.get(current.point) + neighbour.weight;
                
                if (!distances.containsKey(neighbour.point))
                {
                    distances.put(neighbour.point, newDistanceToNeighbour);
                    queue.offer(new Vertex(neighbour.point, newDistanceToNeighbour));
                } else
                {
                    int currentDistanceToNeighbour = distances.get(neighbour.point);
                    
                    if (newDistanceToNeighbour < currentDistanceToNeighbour)
                    {
                        distances.put(neighbour.point, newDistanceToNeighbour);
                        queue.offer(new Vertex(neighbour.point, newDistanceToNeighbour));
                    }
                }
            }
        }
        
        return distances;
    }
    
    private static void fakeInput()
    {
        String input = "5 8 2\n" +
                "1 2\n" +
                "1 2 5\n" +
                "4 1 2\n" +
                "1 3 1\n" +
                "3 4 4\n" +
                "4 5 1\n" +
                "2 4 3\n" +
                "5 2 1\n" +
                "2 3 20";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    public static void main(String[] args) throws IOException
    {
        fakeInput();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        int[] input = Arrays.stream(reader.readLine().split("\\s"))
                .mapToInt(Integer::parseInt)
                .toArray();
        
        int edges = input[1];
        
        Map<Integer, List<Vertex>> vertices = new HashMap<>();
        Set<Integer> hospitals = Arrays.stream(reader.readLine().split("\\s"))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toCollection(HashSet::new));
        
        for (int i = 0; i < edges; i++)
        {
            int[] currentInput = Arrays.stream(reader.readLine().split("\\s"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            
            addEdge(currentInput, vertices, hospitals);
        }
        
        int minDistance = Integer.MAX_VALUE;
        
        for (int nextHospital : hospitals)
        {
            Map<Integer, Integer> distances = dijkstra(nextHospital, vertices);
            int currentDistanceSum = 0;
            
            for (Integer key : vertices.keySet())
            {
                if (hospitals.contains(key))
                    continue;
                
                currentDistanceSum += distances.get(key);
            }
            
            minDistance = Math.min(minDistance, currentDistanceSum);
        }
        
        System.out.println(minDistance);
    }
}
