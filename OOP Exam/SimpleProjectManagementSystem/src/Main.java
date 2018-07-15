import base.CommandParser;
import base.ProjectManagementSystem;

public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        CommandParser parser = new CommandParserImpl();
        ProjectManagementSystem system = new ProjectManagementSystemImpl();
        Application app = new Application(parser, system);
        app.run();
    }
}