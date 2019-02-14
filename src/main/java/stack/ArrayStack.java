package stack;

public class ArrayStack<T> implements Stack<T> {

    private int size;
    private T[] data;
    private int capacity;

    public ArrayStack(int capacity) {
        data = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    public int size() {
        return size;
    }

    public void push(T t) {
        if(size == capacity){
            return;
        }
        data[size++] = t;
    }

    public T pop() {
        if(size == 0){
            return null;
        }
        return data[--size];
    }

    public static void main(String[] args) {
        Stack<String> stack = new ArrayStack<String>(3);
        stack.push("a");
        stack.push("b");
        stack.push("c");
        System.out.println(stack.pop());
        System.out.println(stack.size());
        System.out.println(stack.pop());
        System.out.println(stack.size());
        System.out.println(stack.pop());
        System.out.println(stack.size());
    }
}
