package tree;

import com.sun.org.apache.regexp.internal.RE;

import java.util.LinkedList;

public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    class Node {
        K key;
        V val;
        Node left, right;
        boolean color;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
            this.color = RED;
        }
    }

    private Node root;

    public void put(K key, V val) {
        root = _put(root, key, val);
        root.color = BLACK;
    }

    private Node _put(Node node, K key, V val) {
        if (node == null) {
            return new Node(key, val);
        } else if (node.key.compareTo(key) < 0) {
            node.right = _put(node.right, key, val);
        } else if (node.key.compareTo(key) > 0) {
            node.left = _put(node.left, key, val);
        } else {
            node.val = val;
        }

        //      O
        //     / \
        //   !R   R
        //O rotateLeft
        if (!isRed(node.left) && isRed(node.right)) {
            node = rotateLeft(node);
        }
        //      O
        //     /
        //    R1
        //   /
        //  R2
        //O rotateRight
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        //      B
        //     / \
        //    R   R
        //O flipColors
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }

    private void flipColors(Node node) {
        node.left.color = BLACK;
        node.right.color = BLACK;
        node.color = RED;
    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        Node leftRight = left.right;
        left.right = node;
        node.left = leftRight;
        left.color = node.color;
        node.color = RED;
        return left;
    }

    private Node rotateLeft(Node node) {
        Node right = node.right;
        Node rightLeft = right.left;
        right.left = node;
        node.right = rightLeft;
        right.color = node.color;
        node.color = RED;
        return right;
    }

    private boolean isRed(Node node) {
        return node != null && node.color == RED;
    }


    public V get(K key) {
        return _get(root, key);
    }

    private V _get(Node node, K key) {
        if (node == null) {
            return null;
        } else if (node.key.compareTo(key) < 0) {
            return _get(node.right, key);
        } else if (node.key.compareTo(key) > 0) {
            return _get(node.left, key);
        } else {
            return node.val;
        }
    }

    public V max() {
        Node node = _max(root);
        if (node == null) {
            return null;
        }
        return node.val;
    }

    private Node _max(Node node) {
        if (node.right == null) {
            return node;
        }
        return _max(node.right);
    }


    public V min() {
        Node node = _min(root);
        if (node == null) {
            return null;
        }
        return node.val;
    }

    private Node _min(Node node) {
        if (node.left == null) {
            return node;
        }
        return _min(node.left);
    }

    public void inOrder() {
        _inOrder(root);
    }

    private void _inOrder(Node node) {
        if (node == null) {
            return;
        }
        _inOrder(node.left);
        System.out.println(node.val);
        _inOrder(node.right);
    }


    public void preOrder() {
        _preOrder(root);
    }

    private void _preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val);
        _preOrder(node.left);
        _preOrder(node.right);
    }


    public void postOrder() {
        _postOrder(root);
    }

    private void _postOrder(Node node) {
        if (node == null) {
            return;
        }
        _postOrder(node.left);
        _postOrder(node.right);
        System.out.println(node.val);
    }

    public void bfs() {
        LinkedList<Node> nodes = new LinkedList<Node>();
        nodes.addLast(root);
        while (!nodes.isEmpty()) {
            Node node = nodes.removeFirst();
            System.out.println(node.val);
            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
        }
    }


    public static void main(String[] args) {
        RBTree<String, String> tree = new RBTree<String, String>();
        for (int i = 0; i < 5; i++) {
            tree.put(i +1+ "", i +1+ "");
        }
//        for (int i = 0; i < 5; i++) {
//            System.out.println(tree.get(i + ""));
//        }
//        System.out.println("max:" + tree.max());
//        System.out.println("min:" + tree.min());
//        tree.postOrder();

//        tree.removeMax();
//        tree.removeMin();
//        System.out.println(tree.max());
//        System.out.println(tree.min());
//        tree.bfs();
        tree.bfs();
    }
}
