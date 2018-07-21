import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreaterElement
{
    public static int[] nextGreaterElement(int[] nums1, int[] nums2)
    {
        // map from x to next greater element of x
        Map<Integer, Integer> greaterElements = new HashMap<>();
        
        Stack<Integer> stack = new Stack<>();
        
        for (int num : nums2)
        {
            while (!stack.isEmpty() && stack.peek() < num)
                greaterElements.put(stack.pop(), num);
            
            stack.push(num);
        }
        
        for (int i = 0; i < nums1.length; i++)
            nums1[i] = greaterElements.getOrDefault(nums1[i], -1);
        
        return nums1;
    }
    
    public static void main(String[] args)
    {
        System.out.println(Arrays.toString(nextGreaterElement(
                new int[]{2, 4},
                new int[]{1, 2, 3, 4})));
    }
}