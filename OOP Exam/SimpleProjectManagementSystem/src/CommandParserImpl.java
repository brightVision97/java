import base.CommandParser;
import commands.Command;
import commands.CommandType;

import java.util.Arrays;

public class CommandParserImpl implements CommandParser
{
    @Override
    public Command parseCommand(String commandString)
    {
        String[] commandParts = commandString.split(" ");
        
        CommandType commandType = CommandType.fromName(commandParts[0]);
        String[] args = Arrays.copyOfRange(commandParts, 1, commandParts.length);
        return new Command(commandType, args);
    }
}