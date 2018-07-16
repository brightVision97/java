import java.io.ByteArrayInputStream;

public class FindPassword
{
    static void fakeInput()
    {
        String input = "123456 4";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    public static void main(String[] args)
    {
        fakeInput();
        FastInputReader input = new FastInputReader(System.in);
        
        int digit = input.readInt();
        String digitToStr = String.valueOf(digit);
        
        StringBuilder shifted = new StringBuilder(digitToStr);
        for (int i = 0; i < digitToStr.length() / 2; i++)
        {
            shifted.setCharAt(i, digitToStr.charAt(digitToStr.length() - i - 1));
            shifted.setCharAt(digitToStr.length() - i - 1, digitToStr.charAt(i));
        }
        
        int shiftedInt = Integer.parseInt(shifted.toString());
        int inputInt = input.readInt();
        
        int division = shiftedInt / inputInt;
        int remainder = shiftedInt % inputInt;
        
        System.out.println(division + remainder);
    }
}