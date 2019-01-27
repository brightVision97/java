import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CokiProducts
{
	private static DecimalFormat formatter;
	
	private CokiProducts()
	{
	}
	
	public static DecimalFormat getFormatter()
	{
		return formatter == null
				? new DecimalFormat("##0.00##")
				: formatter;
	}
	
	void fakeInput()
	{
		String input = "5\n" +
				"milk 1.2\n" +
				"orange juice 2.9\n" +
				"icecream 2\n" +
				"cake 5.1\n" +
				"beer 1.2\n" +
				"5\n" +
				"2 beer, 3 orange juice\n" +
				"milk, cake\n" +
				"icecream, 2 cake\n" +
				"icecream, icecream, 3 icecream\n" +
				"5 orange juice, 3 orange juice, orange juice, orange juice";
		System.setIn(new ByteArrayInputStream(input.getBytes()));
	}
	
	private Map<String, Double> pricesDict(List<String> items)
	{
		var map = new HashMap<String, Double>();
		items.stream()
				.map(Object::toString)
				.map(str -> str.split(" "))
				.forEach(item ->
				{
					Stack<String> stack = Arrays.stream(item)
							.collect(Collectors.toCollection(Stack::new));
					double price = Double.parseDouble(stack.pop());
					String joinned = String.join(" ", stack);
					
					map.put(joinned, price);
				});
		
		return map;
	}
	
	public String formatSum(double sum)
	{
		return getFormatter().format(sum);
	}
	
	private void main() throws IOException
	{
		final int[] delimitingIndex = {0};
		var input = Stream.empty()
				.map(Object::toString)
				.collect(Collectors.toList());
		
		fakeInput();
		try (var reader = new BufferedReader(new InputStreamReader(System.in)))
		{
			input = reader.lines()
					.filter(line ->
					{
						if (line.length() == 1)
							delimitingIndex[0] = line.charAt(0) - '0';
						return line.length() > 1;
					}).collect(Collectors.toList());
		}
		
		var items = input.subList(0, delimitingIndex[0]);
		var baskets = input.subList(delimitingIndex[0], input.size());
		var prices = pricesDict(items);
		baskets.forEach(products ->
		{
			double sum = 0.0;
			for (String product : products.split(", "))
			{
				final int[] multiplier = {1};
				final String[] item = {null};
				product.chars()
						.filter(Character::isDigit)
						.findAny()
						.ifPresentOrElse(ch ->
						{
							multiplier[0] = ch - '0';
							item[0] = product.substring(2);
						}, () -> item[0] = product);
				sum += (multiplier[0] * prices.get(item[0]));
			}
			System.out.println(formatSum(sum));
		});
	}
	
	public static void main(String[] args) throws IOException
	{
		new CokiProducts().main();
	}
}

