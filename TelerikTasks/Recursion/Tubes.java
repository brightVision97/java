import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Tubes
{
    static void fakeInput()
    {
        String input = "4\n" +
                "11\n" +
                "803\n" +
                "777\n" +
                "444\n" +
                "555";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    static int binarySearchRecursive(int[] arr, int x, int left, int right)
    {
        int mid = (left + right) / 2;
        
        if (left > right)
            return mid;
        
        int tubes = 0;
        for (int i = 0; i < arr.length; i++)
            tubes += (arr[i] / mid);
        
        if (tubes < x)
            return binarySearchRecursive(arr, x, left, mid - 1);
        else
            return binarySearchRecursive(arr, x, mid + 1, right);
    }
    
    static int binarySearchRecursive(int[] arr, int x)
    {
        return binarySearchRecursive(arr, x, 1, arr[arr.length - 1]);
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
            for (int j = n - 1; j >= 0; j--)
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
        
        System.out.println(binarySearchRecursive(sizes, tubes));
    }
}