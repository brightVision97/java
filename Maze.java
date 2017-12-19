package DataStructures.TaskMatrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Maze
{
    private static final String INPUT_FILE_NAME = "C://Problem2.in";
    private static final String OUTPUT_FILE_NAME = "C://Problem2.out";

    private class Cell
    {
        int row;
        int col;
        int distance;

        public Cell(int row, int col, int distance)
        {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }

    private char[][] maze;
    private ArrayList<Cell> validExitBoundaries;
    private boolean[][] visited;
    private int pathCounter;
    private int size;
    private Cell startCell = null;

    public void readFromFile(String fileName) throws FileNotFoundException
    {
        Scanner input = null;
        try
        {
            input = new Scanner(new File(fileName));
            this.size = input.nextInt();
            input.nextLine();

            this.maze = new char[this.size][this.size];
            this.validExitBoundaries = new ArrayList<Cell>();
            this.visited = new boolean[this.size][this.size];
            this.pathCounter = 0;

            for (int row = 0; row < this.size; row++)
            {
                String line = input.nextLine();
                for (int col = 0; col < line.length(); col++)
                {
                    char ch = line.charAt(col);
                    maze[row][col] = ch;
                    if (ch == '*')
                        this.startCell = new Cell(row, col, 0);
                    if (row == 0 || row == size - 1 || col == 0 || col == size - 1)
                        if (ch == '0')
                            this.validExitBoundaries.add(new Cell(row, col, 0));
                }
            }
        } finally
        {
            if (input != null)
                input.close();
        }
    }

    public int findShortestPath(Cell startCell)
    {
        // if start cell is missing => no path
        if (startCell == null)
            return -1;

        Queue<Cell> visitedCells = new LinkedList<Cell>();
        visitCell(visitedCells, startCell.row, startCell.col, 0);

        // BFS
        while (!visitedCells.isEmpty())
        {
            Cell currentCell = visitedCells.remove();
            int row = currentCell.row;
            int col = currentCell.col;
            int distance = currentCell.distance;
            // checking for borders
            if ((row == 0 || row == size - 1) || (col == 0 || col == size - 1))
                return distance + 1;

            visitCell(visitedCells, row, col + 1, distance + 1);
            visitCell(visitedCells, row, col - 1, distance + 1);
            visitCell(visitedCells, row + 1, col, distance + 1);
            visitCell(visitedCells, row - 1, col, distance + 1);

        }

        // no cell at the border was reached => no path
        return -1;
    }

    private void visitCell(Queue<Cell> visitedCells,
                           int row, int col,
                           int distance)
    {
        if (maze[row][col] != 'x')
        {
            maze[row][col] = 'x';
            Cell cell = new Cell(row, col, distance);
            visitedCells.add(cell);
        }
    }

    public void saveResult(String fileName, int result, int pathCounter) throws IOException
    {
        FileWriter writer = new FileWriter(fileName);
        try
        {
            writer.write("" + result + " " + pathCounter);
        } finally
        {
            if (writer != null)
                writer.close();
        }
    }

    public void replacePossibleExits()
    {
        for (int i = 0; i < validExitBoundaries.size(); i++)
            this.maze[validExitBoundaries.get(i).row][validExitBoundaries.get(i).col] = 'e';
    }

    public void searchPaths(int i, int j)
    {
        if (i < 0 || i > this.size - 1)
            return;
        if (j < 0 || j > this.size - 1)
            return;
        if (this.maze[i][j] == 'x')
            return;
        if (visited[i][j])
            return;

        visited[i][j] = true;

        if (!(i == this.startCell.row - 1 && j == this.startCell.col - 1))
            if (i == 0 || i == this.size - 1 || j == 0 || j == this.size - 1)
                this.pathCounter++;

        searchPaths(i,j + 1);
        searchPaths(i - 1, j);
        searchPaths(i,j - 1);
        searchPaths(i + 1, j);
    }

    public void processValidPaths()
    {
        for (int i = 0; i < this.validExitBoundaries.size(); i++)
            searchPaths(this.validExitBoundaries.get(i).row,
                    this.validExitBoundaries.get(i).col);
    }

    public int countPaths()
    {
        replacePossibleExits();
        processValidPaths();
        return this.pathCounter;
    }

    public void print()
    {
        for (int i = 0; i < this.size; i++)
        {
            for (int j = 0; j < this.size; j++)
                System.out.print(this.maze[i][j] + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException
    {
        Maze maze = new Maze();
        maze.readFromFile(INPUT_FILE_NAME);
        int pathLength = maze.findShortestPath(maze.startCell);
        int pathCnt = maze.countPaths();
        maze.saveResult(OUTPUT_FILE_NAME, pathLength, pathCnt);
    }
}
