package tree;

import java.util.LinkedList;

public class BinaryTree<K extends Comparable<K>, V> {

    class Node {
        K key;
        V val;
        Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private Node root;

    public void put(K key, V val) {
        root = _put(root, key, val);
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
        return node;
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

    public void removeMin() {
        root = _removeMin(root);
    }

    private Node _removeMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = _removeMax(node.left);
        return node;
    }


    public void removeMax() {
        root = _removeMax(root);
    }

    private Node _removeMax(Node node) {
        if (node.right == null) {
            return node.left;
        }
        node.right = _removeMax(node.right);
        return node;
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

    public void remove(K key) {
        root = _remove(root, key);
    }

    private Node _remove(Node node, K key) {
        if (key.compareTo(node.key) < 0) {
            node.left = _remove(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = _remove(node.right, key);
        } else {
            if (node.right == null) {
                return node.left;
            } else if (node.left == null) {
                return node.right;
            } else {
                Node rightMinNode = _min(node.right);
                node.right = _removeMin(node.right);
                rightMinNode.left = node.left;
                rightMinNode.right = node.right;
                node.left = node.right = null;
                return rightMinNode;
            }
        }
        return node;
    }


    public static void main(String[] args) {
        BinaryTree<String, String> tree = new BinaryTree<String, String>();
        for (int i = 0; i < 5; i++) {
            tree.put(i + "", i + "");
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
        tree.remove("1");
        tree.remove("3");
        tree.bfs();
    }
}
