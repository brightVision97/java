import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.Scanner;

public class SolveExpression
{
    static void fakeInput()
    {
        String input = "12*(35-(46*(5+15)))";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
    
    // Recursive descent parser
    static BigDecimal evaluate(String expression)
    {
        return new Object()
        {
            int pos = -1, ch;
            
            void nextChar()
            {
                ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
            }
            
            boolean eat(int charToEat)
            {
                while (ch == ' ')
                    nextChar();
                
                if (ch == charToEat)
                {
                    nextChar();
                    return true;
                }
                
                return false;
            }
            
            BigDecimal parse()
            {
                nextChar();
                return parseExpression();
            }
            
            BigDecimal parseExpression()
            {
                BigDecimal x = parseTerm();
                
                for (;;)
                {
                    if (eat('+'))
                        x = x.add(parseTerm());
                    else if (eat('-'))
                        x = x.subtract(parseTerm());
                    else
                        return x;
                }
            }
            
            BigDecimal parseTerm()
            {
                BigDecimal x = parseFactor();
                
                for (;;)
                {
                    if (eat('*'))
                        x = x.multiply(parseFactor());
                    else
                        return x;
                }
            }
            
            BigDecimal parseFactor()
            {
                if (eat('+'))
                    return parseFactor();
                if (eat('-'))
                    return parseFactor().negate();
                
                BigDecimal x = BigDecimal.ZERO;
                int startPos = this.pos;
                
                if (eat('('))
                {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.')
                {
                    while ((ch >= '0' && ch <= '9') || ch == '.')
                        nextChar();
                    
                    x = BigDecimal.valueOf(Double.parseDouble(expression.substring(startPos, this.pos)));
                }
                
                return x;
            }
        }.parse();
    }
    
    public static void main(String[] args)
    {
        fakeInput();
        Scanner input = new Scanner(System.in);
    
        System.out.println((evaluate(input.nextLine())).toBigInteger());
    }
}