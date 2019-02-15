package queue;

public interface Queue<T> {

    int size();

    void enqueue(T t);

    T dequeue();
}
