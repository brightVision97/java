package commands;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Command
{
    private String[] params;
    private CommandType commandType;
    
    public Command(CommandType type)
    {
        setCommandType(type);
    }
    
    public Command(CommandType type, String[] params)
    {
        this(type);
        setParams(params);
    }
    
    public CommandType getCommandType()
    {
        return commandType;
    }
    
    private void setCommandType(CommandType commandType)
    {
        List<CommandType> commands = Arrays.stream(CommandType.values())
                .collect(Collectors.toList());
        
        if (!commands.contains(commandType))
            this.commandType = CommandType.INVALID;
        else
            this.commandType = commandType;
    }
    
    public String[] getParams()
    {
        return params;
    }
    
    private void setParams(String[] params)
    {
        this.params = params;
    }
}
