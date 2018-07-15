package commands;

public enum CommandType
{
    ADD_TASK, ADD_TICKET, ADD_TODO, UPDATE_TODO, LIST_ALL, LIST_TODOS,
    LIST_TODOS_NOT_DONE, LIST_TICKETS, LIST_TASKS, SEARCH, REMOVE, INVALID, EXIT;
    
    private static final String TYPE_ADD_TASK = "ADD-TASK";
    private static final String TYPE_ADD_TICKET = "ADD-TICKET";
    private static final String TYPE_ADD_TODO = "ADD-TODO";
    private static final String TYPE_UPDATE_TODO = "UPDATE-TODO";
    private static final String TYPE_LIST_ALL = "LIST-ALL";
    private static final String TYPE_LIST_TODOS = "LIST-TODOS";
    private static final String TYPE_LIST_TODOS_NOT_DONE = "LIST-TODOS-NOT-DONE";
    private static final String TYPE_LIST_TICKETS = "LIST-TICKETS";
    private static final String TYPE_LIST_TASKS = "LIST-TASKS";
    private static final String TYPE_SEARCH = "SEARCH";
    private static final String TYPE_REMOVE = "REMOVE";
    private static final String TYPE_EXIT = "EXIT";
    
    public static CommandType fromName(String name)
    {
        switch (name)
        {
            case TYPE_ADD_TASK:
                return ADD_TASK;
            case TYPE_ADD_TICKET:
                return ADD_TICKET;
            case TYPE_ADD_TODO:
                return ADD_TODO;
            case TYPE_UPDATE_TODO:
                return UPDATE_TODO;
            case TYPE_LIST_ALL:
                return LIST_ALL;
            case TYPE_LIST_TODOS:
                return LIST_TODOS;
            case TYPE_LIST_TODOS_NOT_DONE:
                return LIST_TODOS_NOT_DONE;
            case TYPE_LIST_TICKETS:
                return LIST_TICKETS;
            case TYPE_LIST_TASKS:
                return LIST_TASKS;
            case TYPE_SEARCH:
                return SEARCH;
            case TYPE_REMOVE:
                return REMOVE;
            case TYPE_EXIT:
                return EXIT;
            default:
                return null;
        }
    }
}
