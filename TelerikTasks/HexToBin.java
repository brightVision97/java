import java.util.Scanner;

/**
 * The task is simple: convert a number in hexadecimal to a number in binary
 */
public class HexToBin
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        
        String bin = input.nextLine();
        
        StringBuilder binary = new StringBuilder();
        
        for (int i = 0; i < bin.length(); i++)
        {
            if (bin.charAt(i) == '0')
            {
                if (i == 0)
                    binary = binary.append("0");
                else
                    binary = binary.append("0000");
            } else if (bin.charAt(i) == '1')
            {
                if (i == 0)
                    binary = binary.append("1");
                else
                    binary = binary.append("0001");
            } else if (bin.charAt(i) == '2')
            {
                if (i == 0)
                    binary = binary.append("10");
                else
                    binary = binary.append("0010");
            } else if (bin.charAt(i) == '3')
            {
                if (i == 0)
                    binary = binary.append("11");
                else
                    binary = binary.append("0011");
            } else if (bin.charAt(i) == '4')
            {
                if (i == 0)
                    binary = binary.append("100");
                else
                    binary = binary.append("0100");
            } else if (bin.charAt(i) == '5')
            {
                if (i == 0)
                    binary = binary.append("101");
                else
                    binary = binary.append("0101");
            } else if (bin.charAt(i) == '6')
            {
                if (i == 0)
                    binary = binary.append("110");
                else
                    binary = binary.append("0110");
            } else if (bin.charAt(i) == '7')
            {
                if (i == 0)
                    binary = binary.append("111");
                else
                    binary = binary.append("0111");
            } else if (bin.charAt(i) == '8')
                binary = binary.append("1000");
            
            else if (bin.charAt(i) == '9')
                binary = binary.append("1001");
            
            else if (bin.charAt(i) == 'A')
                binary = binary.append("1010");
            
            else if (bin.charAt(i) == 'B')
                binary = binary.append("1011");
            
            else if (bin.charAt(i) == 'C')
                binary = binary.append("1100");
            
            else if (bin.charAt(i) == 'D')
                binary = binary.append("1101");
            
            else if (bin.charAt(i) == 'E')
                binary = binary.append("1110");
            
            else if (bin.charAt(i) == 'F')
                binary = binary.append("1111");
        }
        
        System.out.println(binary.toString());
    }
}