package models.base;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class RemindableItem extends Item
{
    private Date dueDate;
    
    public RemindableItem(String title, String description, Date dueDate)
    {
        super(title, description);
        setDueDate(dueDate);
    }
    
    private void setDueDate(Date dueDate)
    {
        Date current = new Date();
        
        if (dueDate.getDate() < current.getDate())
            throw new IllegalArgumentException("Wtf? Due date can't be in the past!");
        
        this.dueDate = dueDate;
    }
    
    @Override
    public String toString()
    {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        
        return super.toString() + "\nDue date: " + format.format(dueDate);
    }
}
