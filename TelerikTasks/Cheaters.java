import inputreader.FastInputReader;

import java.io.ByteArrayInputStream;
import java.util.*;

/**
 * You are given a set of dependencies. Each dependency has two people and a subject.
 * That means, the first person depends on the second in the subject.
 * You are also given a set of commands. Each command asks for a
 * list of dependencies for a person for a subject.
 * Write a task, which by given the dependencies, prints
 * the list of dependencies for each command.
 * <p>
 * Input:
 * <p>
 * Read from the standard input
 * On the first line will be N - the number of dependencies
 * On the next N lines, there will be the dependencies
 * Each dependency consists of three parts X Y Z
 * The means "Person X depends on Person Y on subject Z
 * X and Y can contain any characters, except whitespace
 * Z can contain any characters
 * On line N + 2 there will M - the number of commands
 * On the next M lines, there will be commands
 * Each command consists of two parts - X Z
 * That means "Tell me the dependencies of person X on subject Z"
 * X can contain any characters, except whitespace
 * Z can contain any characters
 * All X and Z will be valid dependencies
 * <p>
 * Output:
 * Print to the standard output
 * For each command, print the list of dependencies.
 * If more than 1 dependency is with the same priority, print them in alphabetical order
 * The name of the dependency should be printed at the rightmost
 */
public class Cheaters
{
    private static class Node
    {
        private String name;
        private boolean isParent;
        
        Node(String name, boolean isParent)
        {
            this.name = name;
            this.isParent = isParent;
        }
    }
    
    private static class TopologyNode
    {
        private String name;
        private Set<String> children;
        private int parentsCount;
        
        TopologyNode(String name)
        {
            this.name = name;
            parentsCount = 0;
            children = new HashSet<>();
        }
    }
    
    private static void addEdge(Map<String, List<Node>> subjectGraph,
                                String x, String y, boolean isParent)
    {
        if (!subjectGraph.containsKey(x))
            subjectGraph.put(x, new ArrayList<Node>());
        
        subjectGraph.get(x).add(new Node(y, isParent));
    }
    
    private static Map<String, Map<String, List<Node>>> readGraph()
    {
        Map<String, Map<String, List<Node>>> graph = new HashMap<>();
        int edges = reader.readInt();
        
        for (int i = 0; i < edges; i++)
        {
            String[] dependancy = reader.readLine().split("\\s");
            
            String x = dependancy[0];
            String y = dependancy[1];
            String subject = dependancy[2];
            
            if (dependancy.length > 3)
            {
                StringBuilder sb = new StringBuilder();
                for (int j = 2; j < dependancy.length; j++)
                    sb.append(dependancy[j]).append(" ");
                
                subject = sb.toString().trim();
            }
            
            if (!graph.containsKey(subject))
                graph.put(subject, new HashMap<>());
            
            addEdge(graph.get(subject), x, y, true);
            addEdge(graph.get(subject), y, x, false);
        }
        
        return graph;
    }
    
    private static Map<String, List<String>> buildSubjectGraphForName(
            Map<String, Map<String, List<Node>>> graph,
            String subject, String name)
    {
        Map<String, List<Node>> subjectGraph = graph.get(subject);
        Map<String, List<String>> subjectGraphNames = new HashMap<>();
        
        Stack<String> stack = new Stack<>();
        stack.push(name);
        
        while (stack.size() > 0)
        {
            String current = stack.pop();
            for (Node next : subjectGraph.get(current))
            {
                if (!next.isParent)
                    continue;
                
                if (!subjectGraphNames.containsKey(next.name))
                    subjectGraphNames.put(next.name, new ArrayList<>());
                
                subjectGraphNames.get(next.name).add(current);
                
                stack.push(next.name);
            }
        }
        
        return subjectGraphNames;
    }
    
    private static List<String> topologicalSort(Map<String, TopologyNode> graph)
    {
        TreeSet<String> sourcesNames = new TreeSet<>();
        
        graph.entrySet().stream()
                .filter(pair -> pair.getValue().parentsCount == 0)
                .forEach(source -> sourcesNames.add(source.getKey()));
        
        List<String> result = new ArrayList<>();
        Set<String> used = new HashSet<>();
        
        while (sourcesNames.size() > 0)
        {
            String source = sourcesNames.pollFirst();
            
            used.add(source);
            result.add(source);
            
            if (!graph.containsKey(source))
                continue;
            
            for (String next : graph.get(source).children)
            {
                if (!graph.containsKey(next) || used.contains(next))
                    continue;
                
                if (--graph.get(next).parentsCount == 0)
                    sourcesNames.add(next);
            }
        }
        
        return result;
    }
    
    private static List<String> findCheatersList(Map<String, Map<String, List<Node>>> allGraph,
                                                 String name, String subject)
    {
        Map<String, List<String>> subjectGraph =
                buildSubjectGraphForName(allGraph, subject, name);
        
        Map<String, TopologyNode> graph = new HashMap<>();
        
        for (Map.Entry<String, List<String>> pair : subjectGraph.entrySet())
        {
            if (!graph.containsKey(pair.getKey()))
                graph.put(pair.getKey(), new TopologyNode(pair.getKey()));
            
            for (String child : pair.getValue())
            {
                if (!graph.containsKey(child))
                    graph.put(child, new TopologyNode(child));
                
                ++graph.get(child).parentsCount;
                
                graph.get(pair.getKey()).children.add(child);
            }
        }
        
        return topologicalSort(graph);
    }
    
    private static void fakeInput()
    {
        String input = "7\n" +
                "Coki Doncho Math\n" +
                "Doncho Coki Graphs\n" +
                "Doncho Yana Math\n" +
                "Stamat Coki Graphs\n" +
                "Doncho Stamat Math\n" +
                "Doncho Coki Dynamic Programming\n" +
                "Stamat Yana Math\n" +
                "6\n" +
                "Coki Math\n" +
                "Doncho Math\n" +
                "Stamat Math\n" +
                "Stamat Graphs\n" +
                "Doncho Dynamic Programming\n" +
                "Coki Dynamic Programming";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    private static FastInputReader reader = null;
    
    public static void main(String[] args)
    {
        fakeInput();
        reader = new FastInputReader(System.in);
        
        Map<String, Map<String, List<Node>>> graph = readGraph();
        int commandsCount = reader.readInt();
        
        for (int i = 0; i < commandsCount; i++)
        {
            String command = reader.readLine();
            int index = command.indexOf(' ');
            String name = command.substring(0, index);
            String subject = command.substring(index + 1);
            
            List<String> result = findCheatersList(graph, name, subject);
            
            if (result.size() == 0)
                System.out.println(name);
            else
                System.out.println(String.join(", ", result));
        }
    }
}