package heap;

public class Heap<T extends Comparable<T>> {

    private int size;
    private T[] data;

    public Heap(int capacity) {
        data = (T[]) new Comparable[capacity];
    }

    public T remvove() {
        T rt = data[0];
        data[0] = data[size - 1];
        shiftDown(0, size - 1);
        return rt;
    }

    private void shiftDown(int index, int limit) {
        while (2 * index + 1 < limit) {
            int k = 2 * index + 1;
            if (k + 1 < limit && data[k].compareTo(data[k + 1]) < 0) {
                k++;
            }
            if (data[k].compareTo(data[index]) > 0) {
                break;
            }
            swap(index, k);
            index = k;
        }
    }

    public T get() {
        return data[0];
    }

    public void add(T t) {
        data[size] = t;
        shiftUp(size);
        size++;
    }

    private void shiftUp(int size) {
        while (size > 0 && data[size].compareTo(data[size / 2]) < 0) {
            swap(size, size / 2);
            size /= 2;
        }
    }

    private void swap(int i1, int i2) {
        T temp = data[i1];
        data[i1] = data[i2];
        data[i2] = temp;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        Heap<String> heap = new Heap<String>(10);
        heap.add("1");
        heap.add("3");
        heap.add("2");

        System.out.println(heap.remvove());
        System.out.println(heap.remvove());
        System.out.println(heap.remvove());
    }
}
