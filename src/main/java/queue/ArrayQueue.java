package queue;

public class ArrayQueue<T> implements Queue<T> {

    private int tail;
    private int head;
    private T[] data;
    private int capacity;

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        data = (T[]) new Object[capacity];
    }

    public int size() {
        return tail - head;
    }

    public void enqueue(T t) {
        if (tail == capacity) {
            if (head == 0) {
                return;
            }
            System.arraycopy(data, head, data, 0, tail - head);
            tail -= head;
            head = 0;
        }
        data[tail++] = t;
    }

    public T dequeue() {
        if (tail == head) {
            return null;
        }
        return data[head++];
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayQueue<Integer>(3);
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
