import base.ArrayDequeBase;
import exceptions.DequeOverflowException;
import exceptions.DequeUnderflowException;

public class ArrayDequeImpl<T> implements ArrayDequeBase<T>
{
    private T[] array;
    private int front;
    private int back;
    private int size;
    
    public ArrayDequeImpl(int capacity)
    {
        if (capacity <= 0)
            throw new IllegalArgumentException("Invalid capacity!");
        
        array = (T[]) new Object[capacity];
        front = 0;
        back = 0;
        size = 0;
    }
    
    @Override
    public void addFirst(T value) throws DequeOverflowException
    {
        if (size == array.length)
            throw new DequeOverflowException("Deque max capacity reached!");
        
        if (isEmpty())
            array[front] = value;
        else
        {
            front = (front - 1 + array.length) % array.length;
            array[front] = value;
        }
        
        ++size;
    }
    
    @Override
    public void addLast(T value) throws DequeOverflowException
    {
        if (size == array.length)
            throw new DequeOverflowException("Deque max capacity reached!");
        
        back = back % array.length;
        array[back] = value;
        
        ++size;
    }
    
    @Override
    public T removeFirst() throws DequeUnderflowException
    {
        if (isEmpty())
            throw new DequeUnderflowException("Deque is empty!");
        
        T toBeRemoved = array[front];
        front = (front + 1) % array.length;
        --size;
        
        return toBeRemoved;
    }
    
    @Override
    public T removeLast() throws DequeUnderflowException
    {
        if (isEmpty())
            throw new DequeUnderflowException("Deque is empty!");
    
        T toBeRemoved = array[back];
        back = (back - 1 + array.length) % array.length;
        --size;
    
        return toBeRemoved;
    }
    
    @Override
    public T getFirst()
    {
        return isEmpty() ? null : array[front];
    }
    
    @Override
    public T getLast()
    {
        return isEmpty() ? null : array[back];
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
}
