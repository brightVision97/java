import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * You are given an online market. Products can be added and queried in the market.
 * You are given a sequence of commands that must be implemented:
 * <p>
 * Commands
 * !add PRODUCT_NAME PRODUCT_PRICE PRODUCT_TYPE – adds a new product to the market
 * -PRODUCT_NAME can be any unique sequence of 3 to 20 characters
 * -PRODUCT_PRICE can be any positive floating-point number, up to 5000
 * -PRODUCT_TYPE can be any sequence of 3 to 20 characters. Product type may not be unique
 * -Print "Ok: Product PRODUCT_NAME added successfully" if the product is added
 * -Print "Error: Product PRODUCT_NAME already exists" if the product already exists
 * !filter by type PRODUCT_TYPE – lists the first 10 products that have the given PRODUCT_TYPE
 * -Print "Error: Type PRODUCT_TYPE does not exists", if the given PRODUCT_TYPE is non-existent
 * !filter by price from MIN_PRICE to MAX_PRICE – lists the first 10 products that have
 * PRODUCT_PRICE in the given range, inclusive
 * !filter by price from MIN_PRICE – lists the first 10 products that have a greater PRODUCT_PRICE
 * than the given, inclusive
 * !filter by price to MAX_PRICE – lists the first 10 products that have a smaller PRODUCT_PRICE
 * that the given, inclusive
 * !end – marks the end of the commands. No commands will follow
 * (!)Info about the commands
 * All products that are listed by the filter commands must be printed in the format
 * "Ok: LIST_OF_PRODUCTS".
 * <p>
 * LIST_OF_PRODUCTS contains the filtered products, separated by a space and a comma (", ")
 * and each product is represented as "PRODUCT_NAME(PRODUCT_PRICE)".
 * <p>
 * If the result from the filtering by price is 0 products, then print "Ok: ".
 * They must also be sorted by the following criteria:
 * First by PRODUCT_PRICE, ascending
 * Then by PRODUCT_NAME, ascending
 * Last by PRODUCT_TYPE, ascending
 */
public class OnlineMarket
{
    private static class Product implements Comparable<Product>
    {
        private String name;
        private String type;
        private double price;
        
        Product(String name, String type, Double price)
        {
            this.name = name;
            this.type = type;
            this.price = price;
        }
        
        @Override
        public int compareTo(Product other)
        {
            int result = Double.compare(price, other.price);
            
            if (result == 0)
                result = name.compareTo(other.name);
            
            if (result == 0)
                result = type.compareTo(other.type);
            
            return result;
        }
        
        @Override
        public String toString()
        {
            BigDecimal formatted = BigDecimal.valueOf(price);
            
            return String.format("%s(%s)", name, formatted.stripTrailingZeros().toPlainString());
        }
    }
    
    private static Map<String, Product> productsByName = new HashMap<>();
    private static Map<String, Set<Product>> productsByType = new HashMap<>();
    private static Set<Product> products = new TreeSet<>();
    private static StringBuilder output = new StringBuilder();
    
    private static void add(String[] params)
    {
        String name = params[1];
        
        if (productsByName.containsKey(name))
            output.append("Error: Product ")
                    .append(name)
                    .append(" already exists")
                    .append("\n");
        else
        {
            String type = params[3];
            double price = Double.parseDouble(params[2]);
            
            Product product = new Product(name, type, price);
            
            if (!productsByType.containsKey(type))
            {
                productsByType.put(type, new TreeSet<>());
                productsByType.get(type).add(product);
            } else
                productsByType.get(type).add(product);
            
            productsByName.put(name, product);
            products.add(product);
            
            output.append("Ok: Product ")
                    .append(name)
                    .append(" added successfully")
                    .append("\n");
        }
    }
    
    private static void filterByPriceFrom(String[] params)
    {
        double price = Double.parseDouble(params[4]);
        
        List<String> result = products.stream()
                .filter(product -> product.price >= price)
                .limit(10)
                .map(Product::toString)
                .collect(Collectors.toList());
        
        output.append("Ok: ")
                .append(String.join(", ", result))
                .append("\n");
    }
    
    private static void filterByPriceTo(String[] params)
    {
        double price = Double.parseDouble(params[4]);
        
        List<String> result = products.stream()
                .filter(product -> product.price <= price)
                .limit(10)
                .map(Product::toString)
                .collect(Collectors.toList());
        
        output.append("Ok: ")
                .append(String.join(", ", result))
                .append("\n");
    }
    
    private static void filterByPriceFromAndTo(String[] params)
    {
        double lowerBound = Double.parseDouble(params[4]);
        double upperBound = Double.parseDouble(params[6]);
        
        List<String> result = products.stream()
                .filter(product -> product.price >= lowerBound && product.price <= upperBound)
                .limit(10)
                .map(Product::toString)
                .collect(Collectors.toList());
        
        output.append("Ok: ")
                .append(String.join(", ", result))
                .append("\n");
    }
    
    private static void filterByType(String type)
    {
        if (!productsByType.containsKey(type))
        {
            output.append("Error: Type ")
                    .append(type)
                    .append(" does not exists")
                    .append("\n");
        } else
        {
            List<String> result = productsByType.get(type).stream()
                    .limit(10)
                    .map(Product::toString)
                    .collect(Collectors.toList());
            
            output.append("Ok: ")
                    .append(String.join(", ", result))
                    .append("\n");
        }
    }
    
    private static void fakeInput()
    {
        String input = "add Milk 1.90 dairy\n" +
                "add Yogurt 1.90 dairy\n" +
                "add Notebook 1111.90 technology\n" +
                "add Orbit 0.90 food\n" +
                "add Rakia 11.90 drinks\n" +
                "add Dress 121.90 clothes\n" +
                "add Jacket 49.90 clothes\n" +
                "add Milk 1.90 dairy\n" +
                "add Socks 2.90 clothes\n" +
                "filter by type dairy\n" +
                "filter by price from 1.00 to 2.00\n" +
                "filter by price from 1.50\n" +
                "filter by price to 2.00\n" +
                "filter by type clothes\n" +
                "end";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    public static void main(String[] args) throws IOException
    {
        fakeInput();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        String command = reader.readLine();
        
        while (!command.equals("end"))
        {
            String[] params = command.trim().split("\\s");
            String commandType = params[0];
            
            switch (commandType)
            {
                case "add":
                    add(params);
                    break;
                case "filter":
                    if (params[2].equals("type"))
                        filterByType(params[3]);
                    else if (params[2].equals("price"))
                    {
                        if (params.length == 7)
                            filterByPriceFromAndTo(params);
                        else
                        {
                            if (params[3].equals("to"))
                                filterByPriceTo(params);
                            else
                                filterByPriceFrom(params);
                        }
                    }
                default:
                    break;
            }
            
            command = reader.readLine();
        }
        
        output.deleteCharAt(output.length() - 1);
        System.out.println(output);
    }
}