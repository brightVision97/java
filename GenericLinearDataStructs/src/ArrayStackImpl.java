import base.ArrayStackBase;

import java.util.Arrays;

public class ArrayStackImpl<T> implements ArrayStackBase<T>
{
    private static final int INITIAL_CAPACITY = 4;
    
    private int size;
    private T[] array;
    
    public ArrayStackImpl()
    {
        this.array = (T[]) new Object[INITIAL_CAPACITY];
    }
    
    @Override
    public void push(T value)
    {
        if (shouldResize())
            increaseCapacity();
        
        array[size++] = value;
    }
    
    @Override
    public T pop()
    {
        int topIndex = --size;
        T value = topIndex >= 0 ? array[topIndex] : null;
        array[topIndex] = null;
        
        return value;
    }
    
    @Override
    public T peek()
    {
        return array[size - 1];
    }
    
    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    @Override
    public int size()
    {
        return size;
    }
    
    private boolean shouldResize()
    {
        return size == array.length;
    }
    
    private void increaseCapacity()
    {
        array = Arrays.copyOf(array, size * 2);
    }
}
