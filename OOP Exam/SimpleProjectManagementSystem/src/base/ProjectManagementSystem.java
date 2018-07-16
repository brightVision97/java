package base;

import models.Priority;
import models.TodoState;
import models.base.Item;

import java.time.LocalDate;
import java.util.List;

public interface ProjectManagementSystem
{
    void addTicket(String title, String description, LocalDate dueDate,
                   Priority priority, String sender, String owner);
    
    void addTodo(String title, String description, TodoState state);
    
    void addTask(String title, String description, LocalDate dueDate,
                 Priority priority, LocalDate plannedTime, String assignee);
    
    List<Item> listAll();
    
    List<Item> listTickets();
    
    List<Item> listTodos();
    
    List<Item> listTodos(TodoState state);
    
    List<Item> listTasks();
    
    List<Item> searchByTitleOrDescription(String pattern);
    
    void changeTodoState(int index, TodoState state);
    
    void removeItem(String itemType, int index);
}
