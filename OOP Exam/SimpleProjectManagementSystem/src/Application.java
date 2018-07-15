import base.CommandParser;
import base.ProjectManagementSystem;
import commands.Command;
import commands.CommandType;
import models.Priority;
import models.TodoState;
import models.base.Item;

import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Application
{
    private static final int DATE_LENGTH = 10;
    private static final int MAX_TODO_PARAMS = 3;
    private static final int MAX_TICKET_PARAMS = 6;
    private static final int MAX_TASK_PARAMS = 6;
    private static final int MAX_UPDATE_PARAMS = 2;
    private static final int MAX_SEARCH_PARAMS = 1;
    private static final int MAX_TICKET_DESCRLESS_PARAMS = 5;
    private static final int MAX_TASK_DESCRLESS_PARAMS = 5;
    private static final int MAX_TODO_DESCRLESS_PARAMS = 2;
    private static final int MAX_DAYS_IN_MONTH = 31;
    private static final int MAX_MONTHS_IN_YEAR = 12;
    private static final int ZERO_YEAR = 0;
    
    private final CommandParser commandParser;
    private ProjectManagementSystem system;
    
    public Application(CommandParser commandParser, ProjectManagementSystem system)
    {
        this.system = system;
        this.commandParser = commandParser;
    }
    
    private static void fakeInput()
    {
        String input = "ADD-TODO wash NOT-DONE" +
                "\nADD-TODO zoo-visit NOT-DONE" +
                "\nADD-TODO breakfast-for-kids NOT-DONE" +
                "\nADD-TASK complete-project 20/07/2018 HIGH 19/07/2018 Doncho" +
                "\nLIST-ALL" +
                "\nEXIT";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    public void run()
    {
        fakeInput();
        Scanner in = new Scanner(System.in);
        while (true)
        {
            String commandString = in.nextLine();
            Command command = this.commandParser.parseCommand(commandString);
            
            // NOTICE => only description is allowed to be empty
            switch (command.getCommandType())
            {
                case ADD_TODO:
                    try
                    {
                        handleAddTodo(command);
                    } catch (IllegalArgumentException ex)
                    {
                        System.err.println(ex.getMessage());
                    }
                    break;
                case ADD_TASK:
                    try
                    {
                        handleAddTask(command);
                    } catch (IllegalArgumentException | ParseException ex)
                    {
                        System.err.println(ex.getMessage());
                    }
                    break;
                case ADD_TICKET:
                    try
                    {
                        handleAddTicket(command);
                    } catch (IllegalArgumentException | ParseException ex)
                    {
                        System.err.println(ex.getMessage());
                    }
                    break;
                case LIST_ALL:
                    handleListAll();
                    break;
                case LIST_TASKS:
                    handleListTasks();
                    break;
                case LIST_TODOS:
                    handleListTodos();
                    break;
                case LIST_TICKETS:
                    handleListTickets();
                    break;
                case LIST_TODOS_NOT_DONE:
                    handleListTodosNotDone();
                    break;
                case UPDATE_TODO:
                    try
                    {
                        handleUpdateTodo(command);
                    } catch (IndexOutOfBoundsException ex)
                    {
                        System.err.println(ex.getMessage());
                    } catch (InputMismatchException ex1)
                    {
                        System.err.println(ex1.getMessage());
                    }
                    break;
                case SEARCH:
                    try
                    {
                        handleSearchByTitleOrDescription(command);
                    } catch (IllegalArgumentException ex)
                    {
                        System.err.println(ex.getMessage());
                    }
                    break;
                case INVALID:
                    System.err.println("Invalid or not full command!");
                    break;
                case EXIT:
                    System.exit(0);
            }
        }
    }
    
    private Date processDate(String date) throws ParseException
    {
        if (date == null)
            throw new IllegalArgumentException("Due date can't be empty!");
        
        if (date.length() != DATE_LENGTH || !date.contains("/"))
            throw new IllegalArgumentException("Due date should be in format dd/MM/yyyy!");
        
        int[] dateParts = Arrays.stream(date.split("/"))
                .mapToInt(Integer::parseInt)
                .toArray();
        
        if (dateParts[0] > MAX_DAYS_IN_MONTH ||
                dateParts[1] > MAX_MONTHS_IN_YEAR ||
                dateParts[2] < ZERO_YEAR)
            throw new IllegalArgumentException("Some of the date parts seem invalid!");
        
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        
        return format.parse(date);
    }
    
    private void handleAddTodo(Command command)
    {
        String description = null;
        
        int indexer = 1;
        boolean noDescription = false;
        
        if (command.getParams().length == MAX_TODO_DESCRLESS_PARAMS)
        {
            description = "";
            noDescription = true;
        } else
        {
            if (command.getParams().length != MAX_TODO_PARAMS)
                throw new IllegalArgumentException("You should give 2 or 3 todo parameters!");
            
            description = command.getParams()[1];
        }
        
        if (!noDescription)
            indexer++;
        
        String title = command.getParams()[0];
        TodoState state = TodoState.fromName(command.getParams()[indexer]);
        
        system.addTodo(title, description, state);
    }
    
    private void handleAddTask(Command command) throws ParseException
    {
        String description = null;
        
        int indexer = 1;
        boolean noDescription = false;
        
        if (command.getParams().length == MAX_TASK_DESCRLESS_PARAMS)
        {
            description = "";
            noDescription = true;
        } else
        {
            if (command.getParams().length != MAX_TASK_PARAMS)
                throw new IllegalArgumentException("You should give 5 or 6 task parameters!");
            
            description = command.getParams()[1];
        }
        
        if (!noDescription)
            indexer++;
        
        String title = command.getParams()[0];
        Date dueDate = processDate(command.getParams()[indexer++]);
        Priority priority = Priority.fromName(command.getParams()[indexer++]);
        Date plannedTime = processDate(command.getParams()[indexer++]);
        String assignee = command.getParams()[indexer];
        
        system.addTask(title, description, dueDate, priority, plannedTime, assignee);
    }
    
    private void handleAddTicket(Command command) throws ParseException
    {
        String description = null;
        
        int indexer = 1;
        boolean noDescription = false;
        
        if (command.getParams().length == MAX_TICKET_DESCRLESS_PARAMS)
        {
            description = "";
            noDescription = true;
        } else
        {
            if (command.getParams().length != MAX_TICKET_PARAMS)
                throw new IllegalArgumentException("You should give minimum 5 or 6 ticket parameters!");
            
            description = command.getParams()[1];
        }
        
        if (!noDescription)
            indexer++;
        
        String title = command.getParams()[0];
        Date dueDate = processDate(command.getParams()[indexer++]);
        Priority priority = Priority.fromName(command.getParams()[indexer++]);
        String sender = command.getParams()[indexer++];
        String owner = command.getParams()[indexer];
        
        system.addTicket(title, description, dueDate, priority, sender, owner);
    }
    
    private void handleListAll()
    {
        system.listAll()
                .forEach(item -> System.out.println(item.toString() + "\n"));
    }
    
    private void handleListTasks()
    {
        system.listTasks()
                .forEach(task -> System.out.println(task.toString() + "\n"));
    }
    
    private void handleListTodos()
    {
        List<Item> todos = system.listTodos();
        
        todos.forEach(todo -> System.out.println(
                (todos.indexOf(todo) + 1) + "." + todo.toString() + "\n"));
    }
    
    private void handleListTickets()
    {
        system.listTickets()
                .forEach(ticket -> System.out.println(ticket.toString() + "\n"));
    }
    
    private void handleListTodosNotDone()
    {
        system.listTodos(TodoState.NOT_DONE)
                .forEach(todo -> System.out.println(todo.toString() + "\n"));
    }
    
    private void handleUpdateTodo(Command command)
    {
        if (command.getParams().length != MAX_UPDATE_PARAMS)
            throw new IllegalArgumentException("You should give 2 todo-update parameters!");
        
        String index = command.getParams()[0];
        TodoState state = TodoState.fromName(command.getParams()[1]);
        
        for (char ch : index.toCharArray())
            if (!Character.isDigit(ch))
                throw new InputMismatchException("You should only give " +
                        "numbers as note indices!");
        
        
        system.changeTodoState(Integer.parseInt(index), state);
    }
    
    private void handleSearchByTitleOrDescription(Command command)
    {
        if (command.getParams().length != MAX_SEARCH_PARAMS)
            throw new IllegalArgumentException("You should give a pattern to search");
        
        String pattern = command.getParams()[0];
        
        system.searchByTitleOrDescription(pattern)
                .forEach(item -> System.out.println(item.toString() + "\n"));
    }
}
