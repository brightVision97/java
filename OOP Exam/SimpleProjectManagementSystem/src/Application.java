import base.CommandParser;
import base.ProjectManagementSystem;
import commands.Command;
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
    private static final int MAX_REMOVE_PARAMS = 2;
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
                "\nREMOVE err" +
                "\nREMOVE ITEM 4" +
                "\nREMOVE TODO 3" +
                "\nLIST-ALL" +
                "\nEXIT";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    public void run() throws InterruptedException
    {
        fakeInput();
        Scanner input = new Scanner(System.in);
        
        while (true)
        {
            String commandString = input.nextLine();
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
                        suspend();
                        System.err.println(ex.getMessage());
                        suspend();
                    }
                    break;
                case ADD_TASK:
                    try
                    {
                        handleAddTask(command);
                    } catch (IllegalArgumentException | ParseException ex)
                    {
                        suspend();
                        if (ex instanceof NumberFormatException)
                            System.err.println("Your input date shouldn't contain non-numeric" +
                                    " characters for either day, month or year!");
                        else
                            System.err.println(ex.getMessage());
                        suspend();
                    }
                    break;
                case ADD_TICKET:
                    try
                    {
                        handleAddTicket(command);
                    } catch (IllegalArgumentException | ParseException ex)
                    {
                        suspend();
                        if (ex instanceof NumberFormatException)
                            System.err.println("Your input date shouldn't contain non-numeric" +
                                    " characters for either day, month or year!");
                        else
                            System.err.println(ex.getMessage());
                        suspend();
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
                    } catch (IllegalArgumentException ex)
                    {
                        suspend();
                        System.err.println(ex.getMessage());
                        suspend();
                    } catch (IndexOutOfBoundsException ex1)
                    {
                        suspend();
                        System.err.println(ex1.getMessage());
                        suspend();
                    } catch (InputMismatchException ex2)
                    {
                        suspend();
                        System.err.println(ex2.getMessage());
                        suspend();
                    }
                    break;
                case SEARCH:
                    try
                    {
                        handleSearchByTitleOrDescription(command);
                    } catch (IllegalArgumentException ex)
                    {
                        suspend();
                        System.err.println(ex.getMessage());
                        suspend();
                    }
                    break;
                case REMOVE:
                    try
                    {
                        handleRemoveItem(command);
                    } catch (IllegalArgumentException ex)
                    {
                        suspend();
                        System.err.println(ex.getMessage());
                        suspend();
                    } catch (InputMismatchException ex1)
                    {
                        suspend();
                        System.err.println(ex1.getMessage());
                        suspend();
                    } catch (IndexOutOfBoundsException ex2)
                    {
                        suspend();
                        System.err.println(ex2.getMessage());
                        suspend();
                    }
                    break;
                case INVALID:
                    System.err.println("Invalid or not full command!");
                    break;
                case EXIT:
                    input.close();
                    System.exit(0);
            }
        }
    }
    
    private static void suspend() throws InterruptedException
    {
        Thread.sleep(50);
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
        List<Item> allItems = new ArrayList<>(system.listAll());
        
        System.out.println();
        allItems.forEach(item -> System.out.println(
                "(" + (allItems.indexOf(item) + 1) + ") " + item.toString() + "\n"));
    }
    
    private void handleListTasks()
    {
        List<Item> tasks = system.listTasks();
        
        System.out.println();
        tasks.forEach(task -> System.out.println(
                "(" + (tasks.indexOf(task) + 1) + ") " + task.toString() + "\n"));
    }
    
    private void handleListTodos()
    {
        List<Item> todos = system.listTodos();
        
        System.out.println();
        todos.forEach(todo -> System.out.println(
                "(" + (todos.indexOf(todo) + 1) + ") " + todo.toString() + "\n"));
    }
    
    private void handleListTickets()
    {
        List<Item> tickets = system.listTickets();
        
        System.out.println();
        tickets.forEach(ticket -> System.out.println(
                "(" + (tickets.indexOf(ticket) + 1) + ") " + ticket.toString() + "\n"));
    }
    
    private void handleListTodosNotDone()
    {
        List<Item> todos = system.listTodos(TodoState.NOT_DONE);
        
        System.out.println();
        todos.forEach(todo -> System.out.println(
                "(" + (todos.indexOf(todo) + 1) + ") " + todo.toString() + "\n"));
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
                        "numbers as item indices!");
        
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
    
    private void handleRemoveItem(Command command)
    {
        if (command.getParams().length != MAX_REMOVE_PARAMS)
            throw new IllegalArgumentException("You should give the item type and its index " +
                    "from the collection containing SAME TYPE objects!\n" +
                    "To get an index, first run the list command for a desired type");
        
        String itemType = command.getParams()[0];
        String index = command.getParams()[1];
        
        for (char ch : index.toCharArray())
            if (!Character.isDigit(ch))
                throw new InputMismatchException("You should only give " +
                        "numbers as item indices!");
        
        system.removeItem(itemType, Integer.parseInt(index));
    }
}
