import java.io.ByteArrayInputStream;
import java.util.*;

public class ArraySearch
{
    static void fakeInput()
    {
        String input = "0,0,0,0";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);
        
        int[] numbers = Arrays.stream(input.nextLine().split(","))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
        
        int[] numsToN = new int[numbers.length];
        for (int i = 1; i <= numbers.length; i++)
            numsToN[i - 1] = i;
        
        List<Integer> missingNums = new ArrayList<>();
       
        for (int i = 0; i < numsToN.length; i++)
        {
            boolean found = false;
            for (int j = 0; j < numbers.length; j++)
            {
                if (numsToN[i] == numbers[j])
                {
                    found = true;
                    break;
                }
                
                if (j == numbers.length - 1 && !found)
                    missingNums.add(numsToN[i]);
            }
        }
    
        StringBuilder sb = new StringBuilder();
        Iterator<Integer> it = missingNums.iterator();
        while (it.hasNext())
            sb.append(it.next()).append(',');
        sb.deleteCharAt(sb.length() - 1);
    
        System.out.println(sb.toString());
    }
}