package queue;

public class ArrayRecycleQueue<T> implements Queue<T> {

    private int head;
    private int tail;
    private T[] data;
    private int size;
    private int capacity;

    public ArrayRecycleQueue(int capacity) {
        this.capacity = capacity+1;
        data = (T[]) new Object[capacity + 1];
    }

    public int size() {
        return size;
    }

    public void enqueue(T t) {
        int nextIndex = (tail + 1) % capacity;
        if (nextIndex == head) {
            return;
        }
        data[tail] = t;
        tail = nextIndex;
        size++;
    }

    public T dequeue() {
        if (head == tail) {
            return null;
        }
        T rt = data[head];
        head = (head + 1) % capacity;
        return rt;
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayRecycleQueue<Integer>(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.dequeue());
        queue.enqueue(4);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }
}
