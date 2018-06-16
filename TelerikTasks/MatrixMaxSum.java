import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class MatrixMaxSum
{
    static void fakeInput()
    {
        String input = "5\n" +
                "1 22 3 41 5 2\n" +
                "2 13 4 -5 6 5\n" +
                "-6 5 9 31 2 8\n" +
                "3 14 5 -6 7 4\n" +
                "4 -5 6 -7 8 7\n" +
                "-3 -3 3 3 4 -3 -4 3";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void main(String[] args)
    {
        fakeInput();
        Scanner scanner = new Scanner(System.in);
        int rows = Integer.parseInt(scanner.nextLine());
        String firstRow = scanner.nextLine();
        String[] firstRowArray = firstRow.split(" ");
        int[][] matrix = new int[rows][firstRowArray.length];
        for (int i = 0; i < firstRowArray.length; i++){
            matrix[0][i] = Integer.parseInt(firstRowArray[i]);
        }
        for (int i = 0; i < rows - 1; i++){
            String[] currentRow = scanner.nextLine().split(" ");
            for (int j = 0; j < firstRowArray.length; j++){
                matrix[i + 1][j] = Integer.parseInt(currentRow[j]);
            }
        }
        String[] tries = scanner.nextLine().split(" ");
        int biggestTry = Integer.MIN_VALUE;
        for (int i = 0; i < tries.length; i+=2){
            int currentSum = 0;
            int currentRow = Integer.parseInt(tries[i]);
            int currentColumn = Integer.parseInt(tries[i+1]);
            if (currentRow >= 0){
                int currentColumnRow = Math.abs(currentColumn);
                for(int j = 0; j < currentColumnRow; j++){
                    currentSum+=matrix[currentRow - 1][j];
                }
            }
            else {
                int currentColumnRow = Math.abs(currentColumn);
                for(int j = currentColumnRow - 1; j < firstRowArray.length; j++){
                    currentSum+=matrix[Math.abs(currentRow) - 1][j];
                }
            }
            if (currentColumn >= 0){
                int currentRowColumn = Math.abs(currentRow);
                for(int j = currentRowColumn - 1; j >= 0; j--){
                    currentSum+=matrix[j][currentColumn - 1];
                }
            }
            else {
                int currentRowColumn = Math.abs(currentRow);
                for(int j = currentRowColumn - 1; j < rows; j++){
                    currentSum+=matrix[j][Math.abs(currentColumn) - 1];
                }
            }
            if (currentSum - matrix[Math.abs(currentRow) - 1][Math.abs(currentColumn) - 1] > biggestTry){
                biggestTry  = currentSum - matrix[Math.abs(currentRow) - 1][Math.abs(currentColumn) - 1];
            }
        }
        System.out.println(biggestTry);

    }
}