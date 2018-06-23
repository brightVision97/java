import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Tubes
{
    static void fakeInput()
    {
        String input = "3\n" +
                "6\n" +
                "100\n" +
                "200\n" +
                "300";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    public static void main(String[] args) throws IOException
    {
        fakeInput();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        int tubes = Integer.parseInt(br.readLine());
        
        int[] sizes = new int[n];
        for (int i = 0; i < n; i++)
            sizes[i] = Integer.parseInt(br.readLine());
        
        int minTubeSize = Arrays.stream(sizes).min().getAsInt();
        int maxTubeSize = Arrays.stream(sizes).max().getAsInt();
        int tubeSizesSum = Arrays.stream(sizes).sum();
        
        Arrays.sort(sizes);
        
        int result = 0;
        
        for (int i = maxTubeSize; i > 0; i--)
        {
            int cuts = 0;
            for (int j = n - 1; j >= 0 ; j--)
            {
                int currentMaxSize = sizes[j] / i;
                cuts += currentMaxSize;
            }
            
            if (cuts >= tubes)
            {
                result = i;
                break;
            }
        }
        
        System.out.println(result);
    }
}