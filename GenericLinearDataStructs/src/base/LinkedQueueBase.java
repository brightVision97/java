package base;

public interface LinkedQueueBase<T>
{
    LinkedQueueBase<T> enqueue(T value);
    
    T dequeue();
    
    boolean isEmpty();
    
    int size();
}
