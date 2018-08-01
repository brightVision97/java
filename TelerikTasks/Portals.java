import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Инате двумерен лабиринт, който е разделен на кубове с размери 1х1.
 * Не може да се преминава от куб в куб, тъй като стените на кубовете са от непробиваем бетон.
 * Което означава и че не може да се излезне от лабиринта (но какво от това).
 * <p>
 * За щастие на намиращият се вътре, почти във всеки от кубовете има портал.
 * Всеки портал си има числова мощ, която показва на какаво разстояние телепортира той.
 * Порталът може да телепортира и в четирите посоки – горе, долу, ляво и дясно (без диагонали).
 * Всеки портал може да бъде използван само веднъж, след което се деактивира.
 * Освен това порталите не могат да телепортират извън лабиринта.
 * Ето няколко примера за валидни телепортации в зависимост от мощта на портала.
 */
public class Portals
{
    private static int best = Integer.MIN_VALUE;
    
    private static void dfs(int x, int y, char[][] field, boolean[][] used, int current)
    {
        int[] dRows = {-field[x][y] + '0', field[x][y] - '0', 0, 0};
        int[] dCols = {0, 0, -field[x][y] + '0', field[x][y] - '0'};
        
        for (int dir = 0; dir < dRows.length; dir++)
        {
            int nextRow = x + dRows[dir];
            int nextCol = y + dCols[dir];
            
            if (nextRow < 0 || nextRow >= field.length ||
                    nextCol < 0 || nextCol >= field[0].length)
                continue;
            
            if (field[nextRow][nextCol] == '#')
                continue;
            
            if (used[x][y])
                break;
            else
                used[x][y] = true;
            
            current += Character.getNumericValue(field[x][y]);
            dfs(nextRow, nextCol, field, used, current);
            current -= Character.getNumericValue(field[x][y]);
            used[x][y] = false;
        }
        
        best = Math.max(current, best);
    }
    
    private static void fakeInput()
    {
        String input = "0 0\n" +
                "5 6\n" +
                "1 # 5 4 6 4\n" +
                "3 2 # 2 6 2\n" +
                "9 1 7 6 3 1\n" +
                "8 2 7 3 8 6\n" +
                "3 6 1 3 1 2";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    public static void main(String[] args) throws IOException
    {
        fakeInput();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        String xy = reader.readLine();
        
        int x = Character.getNumericValue(xy.charAt(0));
        int y = Character.getNumericValue(xy.charAt(2));
        
        String rc = reader.readLine();
        
        int row = Character.getNumericValue(rc.charAt(0));
        int col = Character.getNumericValue(rc.charAt(2));
        
        char[][] field = new char[row][col];
        boolean[][] used = new boolean[row][col];
        
        for (int i = 0; i < field.length; i++)
            field[i] = reader.readLine()
                    .replace(" ", "")
                    .toCharArray();
        
        dfs(x, y, field, used, 0);
        
        System.out.println(best);
    }
}
