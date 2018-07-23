import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import inputreader.FastInputReader;

import java.io.ByteArrayInputStream;
import java.util.LinkedList;

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
    private static LinkedList<String> queue = new LinkedList<>();
    private static Multiset<String> multiset = HashMultiset.create();
    
    private static void append(String name)
    {
        queue.addLast(name);
        multiset.add(name);
        builder.append("OK\n");
    }
    
    private static void insert(int pos, String name)
    {
        if (pos >= 0 && pos <= queue.size())
        {
            queue.add(pos, name);
            multiset.add(name);
            
            builder.append("OK");
        } else
            builder.append("Error");
        builder.append("\n");
    }
    
    private static void find(String name)
    {
        builder.append(multiset.contains(name) ? multiset.count(name) : 0).append("\n");
    }
    
    private static void serve(int numOfPeople)
    {
        if (numOfPeople <= queue.size())
        {
            for (int i = 0; i < numOfPeople; i++)
            {
                String nameToHandle = queue.removeFirst();
                multiset.remove(nameToHandle);
                
                builder.append(nameToHandle + " ");
            }
        } else
            builder.append("Error");
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
    
    private static final StringBuilder builder = new StringBuilder();
    
    public static void main(String[] args)
    {
        fakeInput();
        FastInputReader reader = new FastInputReader(System.in);
        
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
                default:
                    break;
            }
        }
    }
}