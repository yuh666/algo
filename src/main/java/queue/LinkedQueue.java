package queue;

public class LinkedQueue<T> implements Queue<T> {

    class Node {
        T t;
        Node next;
        Node prev;

        public Node(T t, Node next, Node prev) {
            this.t = t;
            this.next = next;
            this.prev = prev;
        }
    }

    private int size;
    private Node head;
    private Node tail;


    public int size() {
        return size;
    }

    public void enqueue(T t) {
        if (size == 0) {
            head = tail = new Node(t, null, null);
        } else {
            Node currHead = this.head;
            this.head = new Node(t, this.head, null);
            currHead.prev = head;
        }
        size++;
    }

    public T dequeue() {
        if (size == 0) {
            return null;
        }
        Node cuurTail = tail;
        tail = tail.prev;
        cuurTail.prev = null;
        tail.next = null;
        size--;
        if (size == 1) {
            tail.prev = tail.next = null;
            head = tail;
        } else if (size == 0) {
            head = tail = null;
        }
        return cuurTail.t;
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedQueue<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }
}
