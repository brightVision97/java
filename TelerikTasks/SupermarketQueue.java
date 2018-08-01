import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In a supermarket we have a very long queue of people.
 * Usually the people are served in the order of their coming.
 * When someone comes, he / she is appended at the end of the queue.
 * When someone is served, he / she is removed from the front of the queue.
 * Sometimes and old lady comes and the waiting people make a place for her somewhere
 * in the queue. Because the queue could become very long (e.g. few thousand people),
 * the supermarket management organizes a lottery and draws a random name from time to time.
 * After each lottery draw, the management wants to know how many persons
 * having the winning name are currently waiting in the queue.
 * <p>
 * Your task is to write a program to help the supermarket to handle the supermarket queue.
 * It should hold a queue of N items numbered from 0 to N-1, where 0 is the front of
 * the queue and N-1 is the position of the last person (end of the queue).
 * A sample queue is given below:
 * <p>
 * position	    0	      1	      2	      3	      4	      5	      6
 * name	      Peter 	Mike	Penka	Doncho	Nakov	Asya	Nakov
 * <p>
 * Your program should be able to process a sequence of the following commands:
 * <p>
 * Append [name] – appends a person with the specified name at the end of the queue.
 * As a result the command prints " OK".
 * Insert [position] [name] – inserts a person with the specified name
 * at the specified position in the queue (position 0 is just before the first
 * person of the queue and position N is just after the last). In case of success,
 * as a result the command prints " OK". In case the position is invalid, the command
 * does nothing and prints " Error" as a result.
 * Find [name] – finds how many people with the specified name are currently
 * waiting in the queue. As a result the command prints an integer number ( 0 or more ).
 * Serve [count] – serves the specified count of people according to their order in the queue.
 * The served people are removed from the front of the queue. In case of success as a result
 * the command prints the names of the served people in format "[name1] [name2] …"
 * (at a single line, separated by space, ordered as in the queue). In case the count is invalid
 * (bigger than the number of people in the queue), the command does nothing
 * and prints " Error" as a result.
 * End – indicates the end of the input commands. Prints nothing and stops the program execution.
 * This command appears only once, at the end of the input sequence of commands.
 */
public class SupermarketQueue
{
    private static List<List<String>> queue = new ArrayList<>();
    private static Map<String, Integer> map = new HashMap<>();
    private static int currentIndex = 0;
    private static int size = 0;
    
    private static void append(String name)
    {
        List<String> toAdd = new ArrayList<>();
        toAdd.add(name);
        queue.add(toAdd);
        
        ++size;
        map.put(name, map.getOrDefault(name, 0) + 1);
        
        builder.append("OK\n");
    }
    
    private static void insert(int pos, String name)
    {
        if (pos < 0 || pos > size)
        {
            builder.append("Error\n");
            return;
        }
        
        if (pos == size)
        {
            append(name);
            return;
        }
        
        if (pos == 0)
        {
            if (size == 0)
                append(name);
            else
            {
                queue.get(currentIndex).add(name);
                
                ++size;
                map.put(name, map.getOrDefault(name, 0) + 1);
                
                builder.append("OK\n");
            }
            return;
        }
        
        int placeToAdd = currentIndex;
        int counter = pos;
        
        for (int i = placeToAdd; i < queue.size(); i++)
        {
            int innerSize = queue.get(i).size();
            if (counter >= innerSize)
            {
                counter -= innerSize;
                continue;
            }
            
            if (counter == 0)
            {
                queue.get(i).add(name);
                
                ++size;
                map.put(name, map.getOrDefault(name, 0) + 1);
                
                builder.append("OK\n");
                return;
            }
            
            queue.get(i).add(size - counter, name);
            
            ++size;
            map.put(name, map.getOrDefault(name, 0) + 1);
            
            builder.append("OK\n");
        }
    }
    
    private static void find(String name)
    {
        builder.append(map.getOrDefault(name, 0)).append("\n");
    }
    
    private static void serve(int numOfPeople)
    {
        if (numOfPeople > size)
        {
            builder.append("Error");
        } else
        {
            int counter = numOfPeople;
            
            while (counter > 0)
            {
                List<String> served = queue.get(currentIndex);
                
                while (!served.isEmpty() && counter > 0)
                {
                    String lastName = served.get(served.size() - 1);
                    served.remove(lastName);
                    builder.append(lastName + " ");
                    
                    map.put(lastName, map.getOrDefault(lastName, 0) - 1);
                    --size;
                    --counter;
                    
                    if (served.isEmpty())
                        ++currentIndex;
                }
            }
        }
        builder.append("\n");
    }
    
    private static void fakeInput()
    {
        String input = "Append Nakov\n" +
                "Serve 1\n" +
                "Find Ina\n" +
                "Append Mike\n" +
                "Insert 0 Peter\n" +
                "Append Penka\n" +
                "Insert 3 Doncho\n" +
                "Serve 5\n" +
                "Append Asya\n" +
                "Insert 4 Nakov\n" +
                "Append Nakov\n" +
                "Find Asya\n" +
                "Find Nakov\n" +
                "Serve 3\n" +
                "Find Peter\n" +
                "Serve 4\n" +
                "Find Nakov\n" +
                "Insert 1 Ina\n" +
                "End";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    private static StringBuilder builder = new StringBuilder();
    
    public static void main(String[] args) throws IOException
    {
        fakeInput();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        while (true)
        {
            String command = reader.readLine();
            
            if (command.equals("End"))
            {
                System.out.println(builder.toString());
                System.exit(0);
            }
            
            String[] params = command.split("\\s");
            switch (params[0])
            {
                case "Append":
                    append(params[1]);
                    break;
                case "Insert":
                    insert(Integer.parseInt(params[1]), params[2]);
                    break;
                case "Find":
                    find(params[1]);
                    break;
                case "Serve":
                    serve(Integer.parseInt(params[1]));
                    break;
            }
        }
    }
}