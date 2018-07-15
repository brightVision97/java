package models.base;

public abstract class Item
{
    private static final int MIN_TITLE_LENGTH = 3;
    private static final int MAX_TITLE_LENGTH = 30;
    private static final int MAX_DESCR_LENGTH = 250;
    private static final int EMPTY_TITLE_LENGTH = 0;
    
    private String title;
    private String description;
    
    public Item(String title, String description)
    {
        setTitle(title);
        setDescription(description);
    }
    
    public String getTitle()
    {
        return title;
    }
    
    private void setTitle(String title)
    {
        if (title == null || title.trim().length() == EMPTY_TITLE_LENGTH)
            throw new IllegalArgumentException("Title should not be empty!");
        
        if (title.length() < MIN_TITLE_LENGTH || title.length() > MAX_TITLE_LENGTH)
            throw new IllegalArgumentException("Title should be between 3 and 30 characters!");
        
        this.title = title;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    private void setDescription(String description)
    {
        if (description == null)
            throw new IllegalArgumentException("Description should exist even if empty!");
        
        if (description.length() > MAX_DESCR_LENGTH)
            throw new IllegalArgumentException("Description should be maximum " +
                    "250 characters long!");
        
        this.description = description;
    }
    
    @Override
    public String toString()
    {
        return "Title: " + title +
                "\nDescription: " + description;
    }
}
