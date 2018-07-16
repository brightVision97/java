package models.base;

import models.Priority;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public abstract class RemindableItem extends Item
{
    private LocalDate dueDate;
    private Priority priority;
    
    public RemindableItem(String title, String description, LocalDate dueDate, Priority priority)
    {
        super(title, description);
        setDueDate(dueDate);
        setPriority(priority);
    }
    
    private void setDueDate(LocalDate dueDate)
    {
        LocalDate current = LocalDate.now();
        
        if (dueDate.isBefore(current))
            throw new IllegalArgumentException("Wtf? Due date can't be in the past!");
        
        this.dueDate = dueDate;
    }
    
    public Priority getPriority()
    {
        return priority;
    }
    
    private void setPriority(Priority priority)
    {
        this.priority = priority;
    }
    
    @Override
    public String toString()
    {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        
        return super.toString() +
                "\nPriority: " + priority.toString() +
                "\nDue date: " + dueDate.toString();
    }
}
