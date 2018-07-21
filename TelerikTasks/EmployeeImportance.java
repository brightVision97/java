import java.util.*;

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
