package models;

import models.base.RemindableItem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task extends RemindableItem
{
    private static final int MIN_ASSIGNEE_LENGTH = 3;
    private static final int MAX_ASSIGNEE_LENGTH = 30;
    private static final int EMPTY_ASSIGNEE_LENGTH = 0;
    
    private String assignee;
    private Date plannedTime;
    private Priority priority;
    
    public Task(String title, String description, Date dueDate,
                Priority priority, Date plannedTime, String assignee)
    {
        super(title, description, dueDate);
        setPriority(priority);
        setPlannedTime(plannedTime);
        setAssignee(assignee);
    }
    
    private void setAssignee(String assignee)
    {
        if (assignee == null || assignee.trim().length() == EMPTY_ASSIGNEE_LENGTH)
            throw new IllegalArgumentException("Assignee can't be empty!");
        
        if (assignee.length() < MIN_ASSIGNEE_LENGTH || assignee.length() > MAX_ASSIGNEE_LENGTH)
            throw new IllegalArgumentException("Assignee length should be between " +
                    "3 and 30 characters long!");
        
        this.assignee = assignee;
    }
    
    private void setPlannedTime(Date plannedTime)
    {
        Date current = new Date();
        
        if (plannedTime.getDate() < current.getDate())
            throw new IllegalArgumentException("Wtf? Planned date can't be in the past!");
        
        this.plannedTime = plannedTime;
    }
    
    private void setPriority(Priority priority)
    {
        this.priority = priority;
    }
    
    @Override
    public String toString()
    {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        
        return "[Task]\n" + super.toString() +
                "\nAssignee: " + assignee +
                "\nPlanned time: " + format.format(plannedTime) +
                "\nPriority: " + priority;
    }
}
