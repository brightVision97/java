package base;

public interface LinkedListBase<T>
{
    void addFirst(T value);
    
    void addLast(T value);
    
    T removeFirst();
    
    T removeLast();
    
    boolean isEmpty();
    
    int size();
}
