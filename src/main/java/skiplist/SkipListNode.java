package skiplist;

/**
 * 跳表
 *
 * @author yuh
 * @date 2019-02-19 09:31
 **/
public class SkipListNode<V> {

    class Node {
        int key;
        V val;
        Node up, down, left, right;

        public Node(int key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private static int MIN = Integer.MIN_VALUE;
    private static int MAX = Integer.MIN_VALUE;

    private Node head;
    private Node tail;
    private int height;

    public SkipListNode() {
        head = new Node(MIN, null);
        tail = new Node(MAX, null);
        linkHori(head, tail);
        height = 1;
    }

    public V get(int key) {
        Node preNode = findPreNode(key);
        if (preNode.key == key) {
            return preNode.val;
        }
        return null;
    }

    public V put(int key, V val) {
        Node preNode = findPreNode(key);
        if (key == preNode.key) {
            V rt = preNode.val;
            preNode.val = val;
            return rt;
        }

        return null;
    }

    private Node findPreNode(int key) {
        Node curr = head;
        while (curr.right != tail && curr.right.key <= key) {
            curr = curr.right;
            if (curr.down != null) {
                curr = curr.down;
            }
        }
        return curr;
    }

    private void linkHori(Node l, Node r) {
        l.right = r;
        r.left = l;
    }


}
