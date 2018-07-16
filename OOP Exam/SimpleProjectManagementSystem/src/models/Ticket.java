package models;

import models.base.RemindableItem;

import java.time.LocalDate;

public class Ticket extends RemindableItem
{
    private static final int MIN_SEND_OWN_LENGTH = 3;
    private static final int MAX_SEND_OWN_LENGTH = 30;
    private static final int EMPTY_SENDER_LENGTH = 0;
    private static final int EMPTY_OWNER_LENGTH = 0;
    
    private String sender;
    private String owner;
    
    public Ticket(String title, String description, LocalDate dueDate,
                  Priority priority, String sender, String owner)
    {
        super(title, description, dueDate, priority);
        setSender(sender);
        setOwner(owner);
    }
    
    private void setSender(String sender)
    {
        if (sender == null || sender.trim().length() == EMPTY_SENDER_LENGTH)
            throw new IllegalArgumentException("Sender can't be empty");
        
        if (sender.length() < MIN_SEND_OWN_LENGTH || sender.length() > MAX_SEND_OWN_LENGTH)
            throw new IllegalArgumentException("Sender length should be between " +
                    "3 and 30 characters!");
        
        this.sender = sender;
    }
    
    private void setOwner(String owner)
    {
        if (owner == null || owner.trim().length() == EMPTY_OWNER_LENGTH)
            throw new IllegalArgumentException("Owner can't be empty");
        
        if (owner.length() < MIN_SEND_OWN_LENGTH || owner.length() > MAX_SEND_OWN_LENGTH)
            throw new IllegalArgumentException("Owner length should be between " +
                    "3 and 30 characters!");
        
        this.owner = owner;
    }
    
    @Override
    public String toString()
    {
        return "[Ticket]\n" + super.toString() +
                "\nSender: " + sender +
                "\nOwner: " + owner;
    }
}
