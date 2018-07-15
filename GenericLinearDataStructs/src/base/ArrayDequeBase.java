package base;

import exceptions.DequeOverflowException;
import exceptions.DequeUnderflowException;

public interface ArrayDequeBase<T>
{
    void addFirst(T value) throws DequeOverflowException;
    
    void addLast(T value) throws DequeOverflowException;
    
    T removeFirst() throws DequeUnderflowException;
    
    T removeLast() throws DequeUnderflowException;
    
    T getFirst();
    
    T getLast();
    
    boolean isEmpty();
    
    int size();
}
