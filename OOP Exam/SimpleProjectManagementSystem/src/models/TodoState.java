package models;

public enum TodoState
{
    DONE, NOT_DONE;
    
    private static final String STATE_DONE = "DONE";
    private static final String STATE_NOT_DONE = "NOT-DONE";
    
    public static TodoState fromName(String name)
    {
        switch (name)
        {
            case STATE_DONE:
                return DONE;
            case STATE_NOT_DONE:
                return NOT_DONE;
            default:
                return null;
        }
    }
}
