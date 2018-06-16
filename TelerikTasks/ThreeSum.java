import java.util.*;

public class ThreeSum
{
    static List<List<Integer>> threeSum(int[] nums)
    {
        Set<List<Integer>> sets = new LinkedHashSet<>();

        for (int i = 0; i < nums.length; i++)
        {
            for (int j = i + 1; j < nums.length; j++)
            {
                for (int k = j + 1; k < nums.length; k++)
                {
                    if (nums[i] + nums[j] + nums[k] == 0)
                    {
                        List<Integer> current = new ArrayList<>();
                        current.add(nums[i]);
                        current.add(nums[j]);
                        current.add(nums[k]);

                        Collections.sort(current);

                        sets.add(current);
                    }
                }
            }
        }

        return new ArrayList<>(sets);
    }

    public static void main(String[] args)
    {

       List<List<Integer>> sets = threeSum(new int[] {-1, 0, 1, 2, -1, -4});

       for (List<Integer> list : sets)
       {
           for (int i = 0; i < list.size(); i++)
               System.out.print(list.get(i) + " ");
           System.out.println();
       }
    }
}