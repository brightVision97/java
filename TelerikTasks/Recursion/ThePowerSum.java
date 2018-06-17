package Recursion;

public class ThePowerSum
{
    static int powerSum(int x, int n, int num)
    {
        int currentPow = (int) Math.pow(num, n);

        if (currentPow < x)
            return powerSum(x, n, num + 1) +
                    powerSum(x - currentPow, n, num + 1);

        return currentPow == x ? 1 : 0;
    }

    public static void main(String[] args)
    {
        System.out.println(powerSum(100, 2, 1));
    }
}