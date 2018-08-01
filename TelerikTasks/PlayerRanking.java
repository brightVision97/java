import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * You are implementing a player ranking system.
 * Each player has a name, type, age and current position.
 * The ranking system should be able to add new players, find all
 * players of certain type and get the ranking in some range (start and end positions).
 * <p>
 * You are given a sequence of commands that must be implemented:
 * <p>
 * !add PLAYER_NAME PLAYER_TYPE PLAYER_AGE PLAYER_POSITION - adds new player to the rank list
 * -PLAYER_NAME can be any sequence from 1 to 20 characters and may not be unique
 * -PLAYER_TYPE can be any sequence from 1 to 10 characters and may not be unique
 * -PLAYER_AGE can be any integer between 10 and 50
 * -PLAYER_POSITION can be any integer between 1 and the current players count
 * plus one (e.g. if the ranking system already has 2 players, PLAYER_POSITION
 * can be 1, 2 or 3). If a player is inserted to an already used position,
 * all players' positions from this position till the end are incremented by one
 * (e.g. if the ranking system has Player1 in poistion 1, Player2 in position 2 and
 * is inserting Player3 in position 1 => Player1 goes to position 2 and Player2 goes to position 3).
 * -Print "Added player PLAYER_NAME to position PLAYER_POSITION"
 * !find PLAYER_TYPE - finds the top 5 units, first ordered by name in ascending order
 * and then by age in descending order
 * -Print the results in the following format "Type PLAYER_TYPE: PLAYER; PLAYER; PLAYER"
 * where PLAYER should be printed in the format "PLAYER_NAME(PLAYER_AGE)".
 * If no players are found just print "Type PLAYER_TYPE: " (ending whit one space).
 * !ranklist START END - prints the rank list from START to END positions
 * -START can be any integer between 1 and current players count.
 * -END can be any integer between 1 and current players count
 * (and will be greater than or equal to START).
 * !end - marks the end of the commands and no other commands will follow afterwards.
 */
public class PlayerRanking
{
    private static class Player implements Comparable<Player>
    {
        private String name;
        private String type;
        private int age;
        private int position;
        
        public Player(String name, String type, int age, int position)
        {
            this.name = name;
            this.type = type;
            this.age = age;
            this.position = position;
        }
        
        @Override
        public int compareTo(Player player)
        {
            if (name.compareTo(player.name) == 0)
                return Integer.compare(player.age, age);
            
            return name.compareTo(player.name);
        }
        
        @Override
        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            
            return sb.append(name)
                    .append("(")
                    .append(age)
                    .append(")")
                    .toString();
        }
    }
    
    private static void fakeInput()
    {
        String input = "add Ivan Aggressive 20 1\n" +
                "add Pesho Defensive 25 2\n" +
                "add Georgi Neutral 30 3\n" +
                "add Stamat Aggressive 22 2\n" +
                "add Stamat Aggressive 40 1\n" +
                "find Aggressive\n" +
                "ranklist 1 5\n" +
                "add Pencho Neutral 33 2\n" +
                "find Neutral\n" +
                "ranklist 1 3\n" +
                "end";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    private static Map<String, Set<Player>> playersByType;
    private static List<Player> playersByPosition;
    private static StringBuilder output;
    
    public static void main(String[] args) throws IOException
    {
        fakeInput();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        init();
        run(reader);
        reader.close();
    }
    
    private static void init()
    {
        playersByType = new HashMap<>();
        playersByPosition = new ArrayList<>();
        output = new StringBuilder();
    }
    
    private static void run(BufferedReader reader) throws IOException
    {
        String[] input = reader.readLine().split("\\s");
        
        while (!input[0].equals("end"))
        {
            switch (input[0])
            {
                case "add":
                    String name = input[1];
                    String type = input[2];
                    int age = Integer.parseInt(input[3]);
                    int position = Integer.parseInt(input[4]);
                    
                    addPlayer(name, type, age, position);
                    break;
                case "find":
                    handleFind(input[1]);
                    break;
                case "ranklist":
                    int from = Integer.parseInt(input[1]);
                    int to = Integer.parseInt(input[2]);
                    
                    handleRankList(from, to);
                    break;
                default:
                    break;
            }
            
            input = reader.readLine().split("\\s");
        }
        
        System.out.println(output);
    }
    
    private static void handleRankList(int from, int to)
    {
        for (int i = from - 1; i < to; i++)
            output.append(i + 1)
                    .append(". ")
                    .append(playersByPosition.get(i))
                    .append("; ");
        
        output.deleteCharAt(output.length() - 1)
                .deleteCharAt(output.length() - 1)
                .append("\n");
    }
    
    private static void handleFind(String type)
    {
        output.append("Type ")
                .append(type)
                .append(": ");
        
        if (!playersByType.containsKey(type))
        {
            output.append("\n");
            return;
        }
        
        playersByType.get(type).stream()
                .limit(5)
                .forEach(player -> output.append(player).append("; "));
        
        output.deleteCharAt(output.length() - 1)
                .deleteCharAt(output.length() - 1)
                .append("\n");
    }
    
    private static void addPlayer(String name, String type, int age, int position)
    {
        Player newPlayer = new Player(name, type, age, position);
        
        if (!playersByType.containsKey(type))
            playersByType.put(type, new TreeSet<>());
        
        playersByType.get(type).add(newPlayer);
        
        if (position > playersByPosition.size())
            playersByPosition.add(newPlayer);
        else
            playersByPosition.add(position - 1, newPlayer);
        
        output.append("Added player ")
                .append(name)
                .append(" to position ")
                .append(position)
                .append("\n");
    }
}