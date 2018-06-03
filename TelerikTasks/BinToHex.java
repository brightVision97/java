import java.util.Scanner;

public class BinToHex
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        String bin = input.nextLine();

        switch (bin.length() % 4)
        {
            case 1:
                bin = "000" + bin;
                break;
            case 2:
                bin = "00" + bin;
                break;
            case 3:
                bin = "0" + bin;
                break;
        }

        StringBuilder hex = new StringBuilder();
        String tmp;

        for (int i = 0; i < bin.length(); i += 4)
        {
            tmp = "";
            tmp = bin.substring(i, i + 4);

            switch (tmp)
            {
                case "0000":
                    hex.append("0");
                    break;
                case "0001":
                    hex.append("1");
                    break;
                case "0010":
                    hex.append("2");
                    break;
                case "0011":
                    hex.append("3");
                    break;
                case "0100":
                    hex.append("4");
                    break;
                case "0101":
                    hex.append("5");
                    break;
                case "0110":
                    hex.append("6");
                    break;
                case "0111":
                    hex.append("7");
                    break;
                case "1000":
                    hex.append("8");
                    break;
                case "1001":
                    hex.append("9");
                    break;
                case "1010":
                    hex.append("A");
                    break;
                case "1011":
                    hex.append("B");
                    break;
                case "1100":
                    hex.append("C");
                    break;
                case "1101":
                    hex.append("D");
                    break;
                case "1110":
                    hex.append("E");
                    break;
                case "1111":
                    hex.append("F");
                    break;
            }
        }

        System.out.println(hex.toString());
    }
}