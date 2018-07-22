package recursion;

/**
 * You are given a maze represented by a char matrix. Cells marked with x are walls
 * and the whitespace char cells are free to visit. The exit is marked with e.
 * <p>
 * Write a program that finds all possible paths to the exit.
 * Entry point: the upper left cell.
 */
public class LabyrinthPaths
{
    static char[][] labyrinth =
            {
                    {' ', ' ', ' ', 'x', ' ', ' ', ' '},
                    {'*', '*', ' ', 'x', ' ', 'x', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', 'x', 'x', 'x', 'x', 'x', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', 'е'}
            };
    
    static char[] path =
            new char[labyrinth.length * labyrinth[0].length];
    static int position = 0;
    
    static void findPath(int row, int col, char direction)
    {
        if (row >= labyrinth.length ||
                col >= labyrinth[0].length ||
                row < 0 || col < 0)
            return;
        
        path[position++] = direction;
        
        if (labyrinth[row][col] == 'е')
            printPath(path, 1, position - 1);
        
        if (labyrinth[row][col] == ' ')
        {
            labyrinth[row][col] = 'v';
            
            findPath(row, col - 1, 'L');
            findPath(row - 1, col, 'U');
            findPath(row, col + 1, 'R');
            findPath(row + 1, col, 'D');
            
            labyrinth[row][col] = ' ';
        }
        
        position--;
    }
    
    static void printPath(char[] path, int startPos, int endPos)
    {
        System.out.print("Found path to the exit: ");
        for (int pos = startPos; pos <= endPos; pos++)
            System.out.print(path[pos]);
        System.out.println();
    }
    
    public static void main(String[] args)
    {
        findPath(0, 0, 's');
    }
}
