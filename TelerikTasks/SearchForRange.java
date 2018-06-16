import java.util.Arrays;
import java.util.stream.Collectors;

public class SearchForRange
{
    static  int[] searchRange(int[] nums, int target)
    {
        int start = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toList())
                .indexOf(target);

        if (start == -1)
            return new int[] {start, -1};

        int end = start;
        for (int i = start + 1; i < nums.length; i++)
            if (nums[i] == target)
                end = i;

        return new int[] {start, end};
    }

    public static void main(String[] args)
    {
        System.out.println(Arrays.toString(searchRange(new int[] {5, 7, 8, 8, 10}, 5)));
    }
}