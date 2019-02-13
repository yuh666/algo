package lru;

/**
 * 链表实现LRU
 *
 * @author yuh
 * @date 2019-02-13 09:23
 **/
public class LinkedLRUCache<K, V> implements LRUCache<K, V> {

    class Node {
        K key;
        V val;
        Node next;

        public Node(K key, V val) {
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

    private int capacity;
    private Node dummy = new Node();
    private int size;


    public LinkedLRUCache(int capacity) {
        this.capacity = capacity;
    }

    public V get(K key) {
        Node curr = dummy;
        while (curr.next != null) {
            if (curr.next.key == key) {
                Node rt = curr.next;
                //remove
                curr.next = rt.next;
                rt.next = null;
                size--;
                //add
                add(rt.key, rt.val);
                return rt.val;
            }
            curr = curr.next;
        }
        return null;
    }

    public void add(K key, V val) {
        if (size == capacity) {
            //remove tail
            Node curr = dummy;
            while (curr.next != null) {
                if (curr.next.next == null) {
                    //tail
                    curr.next = null;
                    size--;
                    break;
                }
                curr = curr.next;
            }
        }
        Node node = new Node(key, val);
        node.next = dummy.next;
        dummy.next = node;
        size++;
    }

    public void print() {
        Node curr = dummy;
        while (curr.next != null) {
            System.out.print(curr.next + "->");
            curr = curr.next;
        }
    }

    public static void main(String[] args) {
        LRUCache<String, String> cache = new LinkedLRUCache<String, String>(3);
        cache.add("a", "a");
        cache.add("b", "b");
        cache.add("c", "c");
        cache.get("a");
        cache.add("f", "f");
        cache.print();
    }
}
