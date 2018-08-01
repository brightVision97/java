import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Steve found a new toy to play with. It's called HDNL (High Definition Native Language).
 * He doesn't know what it is used for, he just finds it interesting. HDNL works by
 * _defining homeomorphic endofunctors mapping submanifolds of a Hilbert space_. Sadly, when
 * Steve is looking at HDNL, he isn't always able to imagine how all it would look in the end.
 * Each line of HDNL is consisted of a letter and a number and opens a tag (like HTML tag).
 * The letter is important, though Steve can't remember why. The number defines the level
 * of nesting. Steve wants to see how he can nest all the tags such that the
 * level of nesting of inner tags is bigger than that of outer tags. Your task
 * is to write a program for Steve which shows nicely indented and closed HDNL tags.
 */
public class HDNLToy
{
    private static void fakeInput()
    {
        String input = "9\n" +
                "a1\n" +
                "b2\n" +
                "c3\n" +
                "d3\n" +
                "e2\n" +
                "f3\n" +
                "g2\n" +
                "h1\n" +
                "i2";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    public static void main(String[] args) throws IOException
    {
        fakeInput();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(reader.readLine());
        
        String[] input = new String[n];
        
        for (int i = 0; i < n; i++)
            input[i] = reader.readLine();
        
        String whitespace = " ";
        String identation = null;
        
        Stack<String> stack = new Stack<>();
        StringBuilder output = new StringBuilder();
        
        for (int i = 0; i < n; i++)
        {
            while (!stack.isEmpty() && Integer.parseInt(stack.peek().substring(1)) >=
                    Integer.parseInt(input[i].substring(1)))
            {
                identation = IntStream.range(0, stack.size() - 1)
                        .mapToObj(k -> whitespace)
                        .collect(Collectors.joining(""));
                output.append(identation)
                        .append("</")
                        .append(stack.pop())
                        .append(">")
                        .append("\n");
            }
            
            stack.push(input[i]);
            
            identation = IntStream.range(0, stack.size() - 1)
                    .mapToObj(k -> whitespace)
                    .collect(Collectors.joining(""));
            output.append(identation)
                    .append("<")
                    .append(stack.peek())
                    .append(">")
                    .append("\n");
        }
        
        while (!stack.isEmpty())
        {
            identation = IntStream.range(0, stack.size() - 1)
                    .mapToObj(k -> whitespace)
                    .collect(Collectors.joining(""));
            output.append(identation)
                    .append("</")
                    .append(stack.pop())
                    .append(">")
                    .append("\n");
        }
        
        System.out.println(output.toString());
    }
}