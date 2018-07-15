import base.ProjectManagementSystem;
import models.*;
import models.base.Item;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
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
    public void addTicket(String title, String description, Date dueDate,
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
    public void addTask(String title, String description, Date dueDate,
                        Priority priority, Date plannedTime, String assignee)
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
            throw new IndexOutOfBoundsException("Index not in boundaries!");
        
        ((Todo) todos.get(index - 1)).setState(state);
    }
}
