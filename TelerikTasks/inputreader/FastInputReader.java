package inputreader;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.InputMismatchException;

public class FastInputReader
{
    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar, numChars;
    
    public FastInputReader(InputStream stream)
    {
        this.stream = stream;
    }
    
    public void close() throws IOException
    {
        stream.close();
    }
    
    public int read()
    {
        if (numChars == -1)
            throw new InputMismatchException();
        if (curChar >= numChars)
        {
            curChar = 0;
            try
            {
                numChars = stream.read(buf);
            } catch (IOException e)
            {
                throw new InputMismatchException();
            }
            
            if (numChars <= 0)
                return -1;
        }
        
        return buf[curChar++];
    }
    
    public int readInt()
    {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        
        int sgn = 1;
        if (c == '-')
        {
            sgn = -1;
            c = read();
        }
        
        int res = 0;
        do
        {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        
        return res * sgn;
    }
    
    public long readLong()
    {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        
        int sgn = 1;
        if (c == '-')
        {
            sgn = -1;
            c = read();
        }
        
        long res = 0;
        do
        {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        
        return res * sgn;
    }
    
    public String readString()
    {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        
        StringBuilder builder = new StringBuilder();
        
        do
        {
            builder.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));
        
        return builder.toString();
    }
    
    private boolean isSpaceChar(int c)
    {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }
    
    private String readLine0()
    {
        StringBuilder builder = new StringBuilder();
        
        int c = read();
        while (c != '\n' && c != -1)
        {
            if (c != '\r')
                builder.appendCodePoint(c);
            c = read();
        }
        return builder.toString();
    }
    
    public String readLine()
    {
        String s = readLine0();
        while (s.trim().length() == 0)
            s = readLine0();
        
        return s;
    }
    
    public String readLine(boolean ignoreEmptyLines)
    {
        if (ignoreEmptyLines)
            return readLine();
        
        return readLine0();
    }
    
    public BigInteger readBigInteger()
    {
        try
        {
            return new BigInteger(readString());
        } catch (NumberFormatException e)
        {
            throw new InputMismatchException();
        }
    }
    
    public char readCharacter()
    {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        
        return (char) c;
    }
    
    public double readDouble()
    {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        
        int sgn = 1;
        if (c == '-')
        {
            sgn = -1;
            c = read();
        }
        
        double res = 0;
        while (!isSpaceChar(c) && c != '.')
        {
            if (c == 'e' || c == 'E')
                return res * Math.pow(10, readInt());
            
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            
            res *= 10;
            res += c - '0';
            c = read();
        }
        
        if (c == '.')
        {
            c = read();
            double m = 1;
            while (!isSpaceChar(c))
            {
                if (c == 'e' || c == 'E')
                    return res * Math.pow(10, readInt());
                
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                
                m /= 10;
                res += (c - '0') * m;
                c = read();
            }
        }
        
        return res * sgn;
    }
    
    public int[] readIntArray(int size)
    {
        int[] array = new int[size];
        for (int i = 0; i < size; i++)
            array[i] = readInt();
        
        return array;
    }
    
    public long[] readLongArray(int size)
    {
        long[] array = new long[size];
        for (int i = 0; i < size; i++)
            array[i] = readLong();
        
        return array;
    }
    
    public double[] readDoubleArray(int size)
    {
        double[] array = new double[size];
        for (int i = 0; i < size; i++)
            array[i] = readDouble();
        
        return array;
    }
    
    public String[] readStringArray(int size)
    {
        String[] array = new String[size];
        for (int i = 0; i < size; i++)
            array[i] = readString();
        
        return array;
    }
    
    public char[][] readTable(int rows, int cols)
    {
        char[][] table = new char[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                table[i][j] = readCharacter();
        
        return table;
    }
    
    public void readIntArrays(int[]... arrays)
    {
        for (int i = 0; i < arrays[0].length; i++)
            for (int j = 0; j < arrays.length; j++)
                arrays[j][i] = readInt();
    }
}