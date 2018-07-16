import base.ProjectManagementSystem;
import models.*;
import models.base.Item;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectManagementSystemImpl implements ProjectManagementSystem
{
    private List<Item> items;
    
    public ProjectManagementSystemImpl()
    {
        setItems(new ArrayList<>());
    }
    
    private void setItems(List<Item> items)
    {
        this.items = items;
    }
    
    @Override
    public void addTicket(String title, String description, LocalDate dueDate,
                          Priority priority, String sender, String owner)
    {
        Item ticket = new Ticket(title, description, dueDate, priority, sender, owner);
        
        items.add(ticket);
    }
    
    @Override
    public void addTodo(String title, String description, TodoState state)
    {
        Item todo = new Todo(title, description, state);
        
        items.add(todo);
    }
    
    @Override
    public void addTask(String title, String description, LocalDate dueDate,
                        Priority priority, LocalDate plannedTime, String assignee)
    {
        Item task = new Task(title, description, dueDate, priority, plannedTime, assignee);
        
        items.add(task);
    }
    
    @Override
    public List<Item> listAll()
    {
        return items.stream()
                .sorted(Comparator.comparing(Item::getTitle))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Item> listTickets()
    {
        return items.stream()
                .filter(item -> item instanceof Ticket)
                .sorted(Comparator.comparing(Item::getTitle))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Item> listTodos()
    {
        return items.stream()
                .filter(item -> item instanceof Todo)
                .sorted(Comparator.comparing(Item::getTitle))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Item> listTodos(TodoState state)
    {
        return listTodos().stream()
                .filter(todo -> ((Todo) todo).getState().equals(state))
                .sorted(Comparator.comparing(Item::getTitle))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Item> listTasks()
    {
        return items.stream()
                .filter(item -> item instanceof Task)
                .sorted(Comparator.comparing(Item::getTitle))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Item> searchByTitleOrDescription(String pattern)
    {
        return items.stream()
                .filter(item -> item.getTitle().contains(pattern) ||
                        item.getDescription().contains(pattern))
                .sorted(Comparator.comparing(Item::getTitle))
                .collect(Collectors.toList());
    }
    
    /* The idea -> prior to updating status, you should list all todoItems which will be listed
       with indices starting from 1. You then enter the update command with the right index */
    @Override
    public void changeTodoState(int index, TodoState state)
    {
        List<Item> todos = listTodos();
        
        if (index < 1 || index > todos.size())
            throw new IndexOutOfBoundsException("Index out of range!");
        
        ((Todo) todos.get(index - 1)).setState(state);
    }
    
    @Override
    public void removeItem(String itemType, int index)
    {
        itemType = itemType.toLowerCase();
        
        if (!itemType.equals("item") && !itemType.equals("todo") &&
                !itemType.equals("task") && !itemType.equals("ticket"))
            throw new IllegalArgumentException("Type parameter didn't match any existing item types!");
        
        Item toDelete = null;
        switch (itemType)
        {
            case "item":
                if (index < 1 || index > items.size())
                    throw new IndexOutOfBoundsException("Index out of range!");
                
                items.remove(index - 1);
                break;
            case "todo":
                List<Item> todos = listTodos();
                
                if (index < 1 || index > todos.size())
                    throw new IndexOutOfBoundsException("Todos index out of range!");
                
                toDelete = todos.get(index - 1);
                items.remove(toDelete);
                break;
            case "task":
                List<Item> tasks = listTodos();
    
                if (index < 1 || index > tasks.size())
                    throw new IndexOutOfBoundsException("Tasks index out of range!");
    
                toDelete = tasks.get(index - 1);
                items.remove(toDelete);
                break;
            case "ticket":
                List<Item> tickets = listTodos();
    
                if (index < 1 || index > tickets.size())
                    throw new IndexOutOfBoundsException("Tickets index out of range!");
    
                toDelete = tickets.get(index - 1);
                items.remove(toDelete);
                break;
        }
    }
}
