package tree;

import java.util.LinkedList;

public class AVLTree<K extends Comparable<K>, V> {

    class Node {
        K key;
        V val;
        Node left, right;
        int height;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
            this.height = 1;
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
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        int factor = balanceFactor(node);
        //       O
        //      / \
        //     O   O
        //    /
        //   O
        //  /
        // O
        if (factor > 1 && balanceFactor(node.left) > 0) {
            node = rotateRight(node);
        }
        //       O
        //      / \
        //     O   O
        //    /
        //   O
        //    \
        //     O
        if (factor > 1 && balanceFactor(node.left) < 0) {
            node.left = rotateLeft(node.left);
            node = rotateRight(node);
        }
        //       O
        //      / \
        //     O   O
        //          \
        //           O
        //            \
        //             O
        if (factor < -1 && balanceFactor(node.right) < 0) {
            node = rotateLeft(node);
        }
        //       O
        //      / \
        //     O   O
        //          \
        //           O
        //          /
        //         O
        if (factor < -1 && balanceFactor(node.right) > 0) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
        }

        return node;
    }

    private Node rotateLeft(Node node) {
        Node right = node.right;
        Node rightLeft = right.left;

        node.right = rightLeft;
        right.left = node;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        right.height = Math.max(height(right.left), height(right.right)) + 1;

        return right;
    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        Node leftRight = left.right;

        node.left = leftRight;
        left.right = node;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        left.height = Math.max(height(left.left), height(left.right)) + 1;

        return left;
    }


    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private int balanceFactor(Node node) {
        return height(node.left) - height(node.right);
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
        Node node1 = _min(root);
        if (node1 != null) {
            root = _remove(root, node1.key);
        }
    }

    public void removeMax() {
        Node node1 = _max(root);
        if (node1 != null) {
            root = _remove(root, node1.key);
        }
    }


    public void bfs() {
        LinkedList<Node> nodes = new LinkedList<Node>();
        nodes.addLast(root);
        int current = root.height;
        while (!nodes.isEmpty()) {
            Node node = nodes.removeFirst();
            System.out.println(node.height);
            if (node.height < current) {
                System.out.println();
                current = node.height;
            }
            System.out.print(node.val+" ");
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
            Node rt = null;
            if (node.right == null) {
                rt = node.left;
            } else if (node.left == null) {
                rt = node.right;
            } else {
                Node rightMinNode = _min(node.right);
                node.right = _remove(node.right, rightMinNode.key);
                rightMinNode.left = node.left;
                rightMinNode.right = node.right;
                node.left = node.right = null;
                rt = rightMinNode;
            }
            node = rt;
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        int factor = balanceFactor(node);
        //       O
        //      / \
        //   ->O   O
        //    /
        //   O
        //  /
        // O
        if (factor > 1 && balanceFactor(node.left) > 0) {
            node = rotateRight(node);
        }
        //       O
        //      / \
        //   ->O   O
        //    /
        //   O
        //    \
        //     O
        if (factor > 1 && balanceFactor(node.left) < 0) {
            node.left = rotateLeft(node.left);
            node = rotateRight(node);
        }
        //       O
        //      / \
        //     O   O<-
        //          \
        //           O
        //            \
        //             O
        if (factor < -1 && balanceFactor(node.right) < 0) {
            node = rotateLeft(node);
        }
        //       O
        //      / \
        //     O   O<-
        //          \
        //           O
        //          /
        //         O
        if (factor < -1 && balanceFactor(node.right) > 0) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
        }

        return node;
    }

    public boolean isBalance() {
        return _isBalance(root);
    }

    private boolean _isBalance(Node node) {
        if (node == null) {
            return true;
        }
        return Math.abs(balanceFactor(node)) <= 1 && _isBalance(node.left) && _isBalance(node.right);
    }


    public static void main(String[] args) {
        AVLTree<String, String> tree = new AVLTree<String, String>();
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
//        tree.remove("1");
//        tree.remove("3");
//        tree.bfs();
//        System.out.println(tree.isBalance());
//        tree.inOrder();
        tree.bfs();
    }
}
