public class TowersOfHanoi
{
	private static void solve(int n, char from, char to, char aux)
	{
		if (n == 1)
		{
			System.out.println("Disk 1: " + from + " -> " + to);
			return;
		}
		
		solve(n - 1, from, aux, to);
		System.out.println("Disk " + n + ": " + from + " -> " + to);
		solve(n - 1, aux, to, from);
	}
	
	public static void solve(int n)
	{
		solve(n, 'A', 'C', 'B');
	}
	
	public static void main(String[] args)
	{
		solve(4);
	}
}
