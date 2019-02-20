package skiplist;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * 跳表
 *
 * @author yuh
 * @date 2019-02-19 09:31
 **/
public class SkipList<V> {

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
    private static int MAX = Integer.MAX_VALUE;

    private Node head;
    private Node tail;
    private int height;

    public SkipList() {
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
        Node newNode = new Node(key, val);
        insertHoriLink(preNode, newNode);
        int currHeight = 1;
        while (Math.random() > 0.5) {
            if (currHeight == height) {
                //新增一个顶层
                Node newHead = new Node(MIN, null);
                Node newTail = new Node(MAX, null);
                linkHori(newHead, newTail);
                linkVert(newHead, head);
                linkVert(newTail, tail);
                head = newHead;
                tail = newTail;
                height++;
            }
            while (preNode.up == null) {
                preNode = preNode.left;
            }
            preNode = preNode.up;
            Node upNode = new Node(key, null);
            linkVert(upNode, newNode);
            insertHoriLink(preNode, upNode);
            newNode = upNode;
            currHeight++;
        }
        return null;
    }



    private void linkVert(Node up, Node down) {
        up.down = down;
        down.up = up;
    }

    private void insertHoriLink(Node preNode, Node newNode) {
        Node right = preNode.right;
        right.left = newNode;
        newNode.right = right;
        preNode.right = newNode;
        newNode.left = preNode;
    }

    private Node findPreNode(int key) {
        Node curr = head;
        while (true) {
            //右面走到头了
            while (curr.right != tail && curr.right.key <= key) {
                curr = curr.right;
            }
            if (curr.down != null) {
                curr = curr.down;
            } else {
                //在最后一行
                break;
            }
        }
        return curr;
    }

    private List<V> tail(int k1, int k2) {
        Node preNode = findPreNode(k1);
        List<V> list = new ArrayList<V>();
        while (preNode != tail) {
            if (preNode.key >= k1 && preNode.key <= k2) {
                list.add(preNode.val);
            }
            if (preNode.key > k2) {
                break;
            }
            preNode = preNode.right;
        }
        return list;
    }


    private void linkHori(Node l, Node r) {
        l.right = r;
        r.left = l;
    }

    private V remove(int key) {
        Node preNode = findPreNode(key);
        if (preNode.key == key) {
            removeNode(preNode);
            V val = preNode.val;
            return val;
        }
        return null;
    }

    private void removeNode(Node node) {
        while (node != null) {
            Node up = node.up;
            delinkHori(node);
            delinkVert(node);
            node = up;
        }
    }

    /**
     * 因为整列都要删除 所以不用连接上下
     *
     * @param node
     */
    private void delinkVert(Node node) {
        node.up = node.down = null;
    }

    private void delinkHori(Node node) {
        Node left = node.left;
        Node right = node.right;
        if (left != null) {
            left.right = right;
        }
        if (right != null) {
            right.left = left;
        }
        node.left = node.right = null;
    }


    public static void main(String[] args) {
        SkipList<String> list = new SkipList<String>();
        list.put(3, "c");
        list.put(2, "b");
        list.put(1, "a");

//        System.out.println(list.get(1));
//        System.out.println(list.get(2));
//        System.out.println(list.get(3));
        System.out.println(list.tail(2, 3));
    }


}
