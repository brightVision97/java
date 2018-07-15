package base;

public interface ArrayStackBase<T>
{
    void push(T value);
    
    T pop();
    
    T peek();
    
    boolean isEmpty();
    
    int size();
}
