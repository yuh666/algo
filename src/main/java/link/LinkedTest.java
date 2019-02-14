package link;

/**
 * 哨兵的作用就是将边缘处理一般化
 */
public class LinkedTest {

    static class Node {
        int val;
        Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
        }
    }

    static Node findMidNode(Node head) {
        Node fast = head, slow = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    static boolean hasCycle(Node head) {
        Node fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    static Node reverse(Node head) {
        Node newHead = null;
        while (head != null) {
            Node next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

    static void print(Node head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println("null");
    }

    static Node merge(Node head1, Node head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        Node dummy = new Node(-1, null);
        Node curr = dummy;
        while (head1 != null || head2 != null) {
            if (head1 == null) {
                curr.next = head2;
                head2 = head2.next;
            } else if (head2 == null) {
                curr.next = head1;
                head1 = head1.next;
            } else if (head1.val < head2.val) {
                curr.next = head1;
                head1 = head1.next;
            } else {
                curr.next = head2;
                head2 = head2.next;
            }
            curr = curr.next;
        }
        return dummy.next;
    }


    static Node delLastKNode(Node head, int k) {
        Node fast = head;
        int i = 1;
        while (fast.next != null && i < k) {
            fast = fast.next;
            i++;
        }
        if (i < k) {
            return head;
        }
        Node slow = head;
        Node prev = null;
        while (fast.next != null) {
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }
        if (prev == null) {
            return head.next;
        }
        prev.next = prev.next.next;
        return head;
    }

    static Node delLastKNodeByDummy(Node head, int k) {
        if (head == null) {
            return null;
        }
        Node dummy = new Node(0, head);
        Node fast = head;
        int i = 1;
        while (fast.next != null && i < k) {
            fast = fast.next;
            i++;
        }
        if (i < k) {
            return head;
        }
        Node slow = head;
        Node prev = dummy;
        while (fast.next != null) {
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }
        prev.next = prev.next.next;
        return dummy.next;
    }

    static boolean isHW(Node head) {
        Node midNode = findMidNode(head);
        Node reverse = reverse(midNode.next);
        Node reverseCopy = reverse;

        boolean success = true;

        while (reverse != null) {
            if (head.val != reverse.val) {
                success = false;
                break;
            }
            head = head.next;
            reverse = reverse.next;
        }
        midNode.next = reverse(reverseCopy);
        return success;
    }


    public static void main(String[] args) {
        Node node1 = new Node(1, null);
        Node node2 = new Node(2, null);
        Node node3 = new Node(1, null);
        Node node4 = new Node(1, null);
        Node node5 = new Node(5, null);
        Node node6 = new Node(6, null);

        node1.next = node2;
        node2.next = node3;
//        node3.next = node4;

//        node4.next = node5;
//        node5.next = node6;

//        print(node1);
//        print(node4);
//        print(merge(node4, node1));
//        print(delLastKNodeByDummy(node1, 3));


        System.out.println(isHW(node1));
    }
}
