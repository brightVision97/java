import base.LinkedQueueBase;

import java.util.NoSuchElementException;

public class LinkedQueueImpl<T> implements LinkedQueueBase<T>
{
    private class Node
    {
        private T value;
        private Node next;
        
        public Node(T value)
        {
            this.value = value;
        }
    }
    
    private Node first;
    private Node last;
    private int size;
    
    public LinkedQueueImpl()
    {
        size = 0;
        first = null;
        last = null;
    }
    
    @Override
    public LinkedQueueBase<T> enqueue(T value)
    {
        Node current = last;
        last = new Node(value);
        
        if (size++ == 0)
            first = last;
        else
            current.next = last;
        
        return this;
    }
    
    @Override
    public T dequeue()
    {
        if (size == 0)
            throw new NoSuchElementException();
        
        T value = first.value;
        first = first.next;
        
        if (--size == 0)
            last = null;
        
        return value;
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
