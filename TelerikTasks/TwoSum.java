import java.io.ByteArrayInputStream;
import java.util.*;
import java.util.stream.Collectors;

public class TwoSum
{
    static void fakeInput()
    {
        String input = "2 7 21 15\n" +
                "28";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    static int[] twoSum(int[] nums, int targetSum)
    {
        Set<Integer> set = new HashSet<>();
        List<Integer> list = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toList());

        for (int num : list)
        {
            if (set.contains(targetSum - num))
            {
                if (targetSum - num == num)
                    return new int[] {list.indexOf(targetSum - num), list.lastIndexOf(num)};

                return new int[] {list.indexOf(targetSum - num), list.indexOf(num)};
            }

            set.add(num);
        }

        return null;
    }

    @Deprecated
    static int[] twoSumNaive(int[] nums, int targetSum)
    {
        int left = 0;
        int right = nums.length - 1;

        while (left < right)
        {
            int currentSum = nums[left] + nums[right];

            if (currentSum == targetSum)
                return new int[] {left, right};
            else if (currentSum < targetSum)
                left++;
            else
                right--;
        }

        return null;
    }

    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);

        int[] nums = Arrays.stream(input.nextLine().split("\\s"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int targetSum = input.nextInt();

        System.out.println(Arrays.toString(twoSum(nums, targetSum)));
    }
}

