package stack;

public class LinkedStack<T> implements Stack<T> {

    class Node{
        T val;
        Node next;

        public Node(T val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
    private int size;
    private Node dummy = new Node(null,null);


    public int size() {
        return size;
    }

    public void push(T t) {
        dummy.next = new Node(t,dummy.next);
        size++;
    }

    public T pop() {
        Node next = dummy.next;
        if(next != null){
            size--;
            dummy.next = next.next;
        }
        return next.val;
    }

    public static void main(String[] args) {
        LinkedStack<String> stack = new LinkedStack<String>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
