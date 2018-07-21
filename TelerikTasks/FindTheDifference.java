import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindTheDifference
{
    public static char findTheDifference(String s, String t)
    {
        List<Character> sList = s.chars()
                .mapToObj(obj -> (char) obj)
                .collect(Collectors.toList());
        
        Map<Character, Boolean> map = new HashMap<>();
        
        for (char ch : t.toCharArray())
        {
            if (sList.contains(ch))
            {
                map.put(ch, true);
                sList.set(sList.indexOf(ch), ' ');
            } else
                map.put(ch, false);
        }
        
        return map.keySet().stream()
                .filter(key -> !map.get(key))
                .collect(Collectors.toList())
                .get(0);
    }
    
    public static void main(String[] args)
    {
        System.out.println(findTheDifference("a", "aa"));
    }
}