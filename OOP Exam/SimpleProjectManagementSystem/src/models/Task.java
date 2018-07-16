package models;

import models.base.RemindableItem;

import java.time.LocalDate;
public class Task extends RemindableItem
{
    private static final int MIN_ASSIGNEE_LENGTH = 3;
    private static final int MAX_ASSIGNEE_LENGTH = 30;
    private static final int EMPTY_ASSIGNEE_LENGTH = 0;
    
    private String assignee;
    private LocalDate plannedTime;
    
    public Task(String title, String description, LocalDate dueDate,
                Priority priority, LocalDate plannedTime, String assignee)
    {
        super(title, description, dueDate, priority);
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
    
    private void setPlannedTime(LocalDate plannedTime)
    {
        LocalDate now = LocalDate.now();
        
        if (plannedTime.isBefore(now))
            throw new IllegalArgumentException("Setting dates in the past is not allowed!");
        
        this.plannedTime = plannedTime;
    }
    
    @Override
    public String toString()
    {
        return "[Task]\n" + super.toString() +
                "\nPlanned time: " + plannedTime.toString() +
                "\nAssignee: " + assignee;
    }
}
