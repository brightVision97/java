import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A website domain like "discuss.leetcode.com" consists of various subdomains.
 * At the top level, we have "com", at the next level, we have "leetcode.com", and at
 * the lowest level, "discuss.leetcode.com". When we visit a domain like
 * "discuss.leetcode.com", we will also visit the parent domains
 * "leetcode.com" and "com" implicitly.
 * <p>
 * Now, call a "count-paired domain" to be a count
 * (representing the number of visits this domain received),
 * followed by a space, followed by the address.
 * An example of a count-paired domain might be "9001 discuss.leetcode.com".
 * <p>
 * We are given a list cpdomains of count-paired domains.
 * We would like a list of count-paired domains,
 * (in the same format as the input, and in any order),
 * that explicitly counts the number of visits to each subdomain.
 * <p>
 * Example :
 * <p>
 * Input:
 * ["9001 discuss.leetcode.com"]
 * Output:
 * ["9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com"]
 */
public class SubdomainVisitCount
{
    public static List<String> subdomainVisits(String[] cpdomains)
    {
        Map<String, Integer> occurrences = new HashMap<>();
        
        for (String domain : cpdomains)
        {
            String[] splitted = domain.split("\\s");
            
            List<String> domains = new ArrayList<>();
            
            domains.add(splitted[1]);
            domains.add(domains.get(0).substring(domains.get(0).indexOf('.') + 1));
            if (domains.get(1).contains("."))
                domains.add(domains.get(1).substring(domains.get(1).indexOf('.') + 1));
            
            int visits = Integer.parseInt(splitted[0]);
            for (String dom : domains)
            {
                if (!occurrences.containsKey(dom))
                    occurrences.put(dom, visits);
                else
                    occurrences.put(dom, occurrences.get(dom) + visits);
            }
        }
        
        List<String> result = new ArrayList<>();
        
        for (Map.Entry<String, Integer> entry : occurrences.entrySet())
            result.add(entry.getValue() + " " + entry.getKey());
        
        return result;
    }
    
    public static void main(String[] args)
    {
        String[] cpdomains = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        
        List<String> subdomainVisitCount = subdomainVisits(cpdomains);
        
        subdomainVisitCount.forEach(System.out::println);
    }
}
