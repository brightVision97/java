import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * You are implementing a game engine. Your game has different kinds of units,
 * which have name, type and attack. The player can create (add) new units,
 * find all units of certain type and find his/her most powerful units.
 * Sometimes a unit is destroyed and it should be removed from the game.
 * <p>
 * You are given a sequence of commands that must be implemented:
 * <p>
 * !add UNIT_NAME UNIT_TYPE UNIT_ATTACK - adds new unit to the game
 * -UNIT_NAME can be any unique sequence from 1 to 30 characters
 * -UNIT_TYPE can be any sequence from 1 to 40 characters and may not be unique
 * -UNIT_ATTACK can be any integer between 100 and 1000, inclusive
 * -Print "SUCCESS: UNIT_NAME added!", if the unit is added successfully
 * -Print "FAIL: UNIT_NAME already exists!", if the unit already exists
 * !remove UNIT_NAME - removes an existing unit with UNIT_NAME from the game
 * -Print "SUCCESS: UNIT_NAME removed!", if the unit was removed successfully
 * -Print "FAIL: UNIT_NAME could not be found!", if the unit does not exist in the game
 * !find UNIT_TYPE - finds the top 10 units, first ordered by attack in
 * -descending order and then by their name in ascending order
 * -Print the results in the following format "RESULT: UNIT, UNIT, UNIT" where UNIT
 * -should be printed in the format "UNIT_NAME[UNIT_TYPE](UNIT_ATTACK)". If no units
 * -are found just print "RESULT: " (ending with one space)
 * !power NUMBER_OF_UNITS - prints the top NUMBER_OF_UNITS most powerful units
 * -currently in the game in the same format as the "find" command (inside joke:
 * -this command should have been "POWER OVERWHELMING!").
 * -If there are less units than requested just return them.
 * !end - marks the end of the commands and no other commands will follow afterwards
 */
public class UnitsOfWork
{
    private static class Unit implements Comparable<Unit>
    {
        private String name;
        private String type;
        private int attack;
        
        Unit(String name, String type, int attack)
        {
            this.name = name;
            this.type = type;
            this.attack = attack;
        }
        
        @Override
        public String toString()
        {
            return name + "[" + type + "](" + attack + ")";
        }
        
        @Override
        public int compareTo(Unit other)
        {
            if (attack - other.attack != 0)
                return other.attack - attack;
            
            return name.compareTo(other.name);
        }
    }
    
    private static Map<String, Unit> units = new HashMap<>();
    private static Map<String, Set<Unit>> unitsByType = new HashMap<>();
    private static Set<Unit> orderedUnits = new TreeSet<>();
    private static StringBuilder output = new StringBuilder();
    
    private static void getByPower(int power)
    {
        output.append("RESULT: ");
        output.append(orderedUnits.stream()
                .limit(power)
                .map(Unit::toString)
                .collect(Collectors.joining(", ")));
        output.append("\n");
    }
    
    static void fakeInput()
    {
        String input = "add TheMightyThor God 100\n" +
                "add Artanis Protoss 250\n" +
                "add Fenix Protoss 200\n" +
                "add Spiderman MutatedHuman 180\n" +
                "add XelNaga God 500\n" +
                "add Wolverine MutatedHuman 200\n" +
                "add Zeratul Protoss 300\n" +
                "add Spiderman MutatedHuman 180\n" +
                "power 3\n" +
                "find Protoss\n" +
                "find God\n" +
                "remove Kerrigan\n" +
                "remove XelNaga\n" +
                "power 3\n" +
                "find Kerrigan\n" +
                "find God\n" +
                "end";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    public static void main(String[] args) throws IOException
    {
        fakeInput();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        while (true)
        {
            String[] input = reader.readLine().split("\\s");
            switch (input[0].toLowerCase())
            {
                case "add":
                    Unit unitToAdd = new Unit(input[1], input[2], Integer.parseInt(input[3]));
                    
                    if (units.containsKey(unitToAdd.name))
                    {
                        output.append("FAIL: " + unitToAdd.name + " already exists!\n");
                        break;
                    }
                    
                    units.put(unitToAdd.name, unitToAdd);
                    
                    if (!unitsByType.containsKey(unitToAdd.type))
                        unitsByType.put(unitToAdd.type, new TreeSet<Unit>());
                    
                    unitsByType.get(unitToAdd.type).add(unitToAdd);
                    orderedUnits.add(unitToAdd);
                    
                    output.append("SUCCESS: " + unitToAdd.name + " added!\n");
                    break;
                case "remove":
                    String name = input[1];
                    
                    if (!units.containsKey(name))
                    {
                        output.append("FAIL: " + name + " could not be found!\n");
                        break;
                    }
                    
                    Unit toRemove = units.get(name);
                    units.remove(toRemove.name);
                    
                    unitsByType.get(toRemove.type).remove(toRemove);
                    orderedUnits.remove(toRemove);
                    
                    output.append("SUCCESS: " + name + " removed!\n");
                    break;
                case "find":
                    output.append("RESULT: ");
                    if (unitsByType.containsKey(input[1]))
                        output.append(unitsByType.get(input[1]).stream()
                                .limit(10)
                                .map(Unit::toString)
                                .collect(Collectors.joining(", ")));
                    output.append("\n");
                    break;
                case "power":
                    getByPower(Integer.parseInt(input[1]));
                    break;
                case "end":
                    System.out.println(output.toString());
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}