package lru;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author yuh
 * @date 2019-02-13 10:55
 **/
public class ArrayLRUCache implements LRUCache<String, String> {

    class Node {
        String key;
        String val;

        public Node(String key, String val) {
            this.key = key;
            this.val = val;
        }

        public Node() {
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Node{");
            sb.append("key=").append(key);
            sb.append(", val=").append(val);
            sb.append('}');
            return sb.toString();
        }
    }

    private Node[] data;
    private int capacity;
    private int tail;
    private int size;
    private LinkedList<Integer> invalid = new LinkedList<Integer>();

    public ArrayLRUCache(int capacity) {
        this.capacity = capacity;
        data = new Node[capacity];
    }

    public String get(String key) {
        for (int i = 0; i < tail; i++) {
            if (data[i] != null && data[i].key == key) {
                Node rt = data[i];
                if (i != tail - 1) {
                    //mark current
                    invalid.add(i);
                    size--;
                    add(key, rt.val);
                }
                return rt.val;
            }
        }
        return null;
    }

    public void add(String key, String val) {
        if (tail == capacity) {
            //release invalid node
            if (!invalid.isEmpty()) {
                Integer index = invalid.removeLast();
                System.arraycopy(data, index + 1, data, index, tail - index - 1);
                tail--;
            }
            if (tail == capacity) {
                System.arraycopy(data, 1, data, 0, tail - 1);
                tail--;
                size--;
            }
        }
        data[tail++] = new Node(key, val);
        size++;
    }

    public void print() {
        System.out.println(Arrays.toString(data));
    }

    public static void main(String[] args) {
        LRUCache<String, String> cache = new ArrayLRUCache(3);
        cache.add("a", "a");
        cache.add("b", "b");
        cache.add("c", "c");
        cache.get("a");
        cache.add("f", "f");
        cache.print();
    }
}
