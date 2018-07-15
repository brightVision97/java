package models.base;

import models.Priority;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public abstract class RemindableItem extends Item
{
    private Date dueDate;
    private Priority priority;
    
    public RemindableItem(String title, String description, Date dueDate, Priority priority)
    {
        super(title, description);
        setDueDate(dueDate);
        setPriority(priority);
    }
    
    private void setDueDate(Date dueDate)
    {
        Date current = new Date();
        
        if (dueDate.getDate() < current.getDate())
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
                "\nDue date: " + format.format(dueDate);
    }
}
