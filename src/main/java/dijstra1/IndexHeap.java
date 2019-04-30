package dijstra1;

public class IndexHeap<T extends Comparable<T>> {

    private int size;
    private T[] data;
    private int[] indexes;
    private int[] reverse;

    public IndexHeap(int capacity) {
        this.data = (T[]) new Comparable[capacity];
        indexes = new int[capacity];
        reverse = new int[capacity];
    }


    public void add(int index, T t) {
        data[index] = t;
        indexes[size] = index;
        shiftUp(size);
        reverse[index] = size;
        size++;
    }

    private void shiftUp(int size) {
        while (size > 0 && data[indexes[size]].compareTo(data[indexes[size / 2]]) < 0) {
            swap(size, size / 2);
            size /= 2;
        }
    }

    private void swap(int i1, int i2) {
        int temp = indexes[i1];
        indexes[i1] = indexes[i2];
        indexes[i2] = temp;

        reverse[indexes[i1]] = i1;
        reverse[indexes[i2]] = i2;
    }

    public T get() {
        return data[indexes[0]];
    }

    public int getIndex() {
        return indexes[0];
    }

    public T remove() {
        T rt = data[indexes[0]];
        data[indexes[0]] = null;
        indexes[0] = indexes[size - 1];
        shiftDown(0, size - 1);
        size--;
        return rt;
    }

    private void shiftDown(int index, int limit) {
        while (2 * index + 1 < limit) {
            int k = 2 * index + 1;
            if (k + 1 < limit && data[indexes[k]].compareTo(data[indexes[k + 1]]) > 0) {
                k++;
            }
            if (data[indexes[k]].compareTo(data[indexes[index]]) > 0) {
                break;
            }
            swap(index, k);
            index = k;
        }
    }

    public void change(int index, T t) {
        data[index] = t;
        int i = reverse[index];
        shiftDown(i, size);
        shiftUp(i);
    }

    public int removeIndex() {
        int rt = indexes[0];
        data[indexes[0]] = null;
        indexes[0] = indexes[size - 1];
        shiftDown(0, size - 1);
        size--;
        return rt;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public T getByIndex(int index) {
        return data[index];
    }

    public boolean contains(int id) {
        return data[id] != null;
    }


    public static void main(String[] args) {
        IndexHeap<Integer> indexHeap = new IndexHeap<Integer>(10);
        indexHeap.add(1, 1);
        indexHeap.add(3, 3);
        indexHeap.add(2, 2);

        indexHeap.change(1, 5);


        System.out.println(indexHeap.remove());
        System.out.println(indexHeap.remove());
        System.out.println(indexHeap.remove());
    }

}
