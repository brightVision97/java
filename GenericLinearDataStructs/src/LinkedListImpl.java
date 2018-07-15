import base.LinkedListBase;

import java.util.NoSuchElementException;

public class LinkedListImpl<T> implements LinkedListBase<T>
{
    private class Node
    {
        T value;
        Node next;
        Node prev;
        
        public Node(T value, Node next, Node prev)
        {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
    
    private Node head;
    private Node tail;
    private int size;
    
    public LinkedListImpl(T value)
    {
        size = 0;
        head = null;
        tail = null;
    }
    
    @Override
    public void addFirst(T value)
    {
        Node temp = new Node(value, head, null);
        
        if (head != null)
            head.prev = temp;
        if (tail == null)
            tail = temp;
        
        ++size;
    }
    
    @Override
    public void addLast(T value)
    {
        Node temp = new Node(value, null, tail);
        
        if (head != null)
            head.next = temp;
        if (tail == null)
            tail = temp;
        
        ++size;
    }
    
    @Override
    public T removeFirst()
    {
        if (isEmpty())
            throw new NoSuchElementException();
        
        Node temp = head;
        head = head.next;
        head.prev = null;
        --size;
        
        return temp.value;
    }
    
    @Override
    public T removeLast()
    {
        if (isEmpty())
            throw new NoSuchElementException();
        
        Node temp = tail;
        tail = tail.prev;
        tail.next = null;
        --size;
        
        return temp.value;
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
