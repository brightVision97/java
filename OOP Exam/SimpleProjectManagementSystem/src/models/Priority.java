package models;

public enum Priority
{
    HIGH, LOW;
    
    private static final String PRIORITY_HIGH = "HIGH";
    private static final String PRIORITY_LOW = "LOW";
    
    public static Priority fromName(String name)
    {
        switch (name)
        {
            case PRIORITY_HIGH:
                return HIGH;
            case PRIORITY_LOW:
                return LOW;
            default:
                return null;
        }
    }
    
    @Override
    public String toString()
    {
        if (this == HIGH)
            return "High";
        
        return "Low";
    }
}
