import java.util.*;

/**
 * You are given a data structure of employee information,
 * which includes the employee's unique id, his
 * importance value and his direct subordinates' id.
 * <p>
 * For example, employee 1 is the leader of employee 2,
 * and employee 2 is the leader of employee 3.
 * They have importance value 15, 10 and 5, respectively.
 * Then employee 1 has a data structure like [1, 15, [2]],
 * and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []].
 * Note that although employee 3 is also a subordinate of
 * employee 1, the relationship is not direct.
 * <p>
 * Now given the employee information of a company, and an
 * employee id, you need to return the total importance
 * value of this employee and all his subordinates.
 * <p>
 * Example:
 * Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
 * Output: 11
 * Explanation:
 * Employee 1 has importance value 5, and he has two direct subordinates:
 * employee 2 and employee 3. They both have importance value 3. So the total
 * importance value of employee 1 is 5 + 3 + 3 = 11
 */
public class EmployeeImportance
{
    private static class Employee
    {
        private int id;
        private int importance;
        private List<Integer> subordinates;
        
        Employee(int id, int importance, List<Integer> subordinates)
        {
            this.id = id;
            this.importance = importance;
            this.subordinates = subordinates;
        }
    }
    
    private static Map<Integer, Employee> transform(List<Employee> employees)
    {
        Map<Integer, Employee> map = new HashMap<>();
        
        employees.forEach(empl -> map.put(empl.id, empl));
        
        return map;
    }
    
    public static int getImportance(List<Employee> employees, int id)
    {
        Map<Integer, Employee> map = transform(employees);
        
        Stack<Integer> stack = new Stack<>();
        
        int totalImportance = 0;
        
        stack.push(id);
        while (!stack.empty())
        {
            int nextId = stack.pop();
            
            totalImportance += map.get(nextId).importance;
            
            for (int subordinateId : map.get(nextId).subordinates)
                stack.push(subordinateId);
        }
        
        return totalImportance;
    }
    
    public static void main(String[] args)
    {
        List<Employee> employees = new ArrayList<>();
        
        employees.add(new Employee(1, 5, List.of(2, 3)));
        employees.add(new Employee(2, 3, new ArrayList<>()));
        employees.add(new Employee(3, 3, new ArrayList<>()));
        
        System.out.println(getImportance(employees, 1));
    }
}
