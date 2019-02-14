package link;

public class LinkedTest {

    static class Node{
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

    static Node findMidNode(Node head){
        Node fast = head,slow = head;
        while(fast != null && fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    static boolean hasCycle(Node head){
        Node fast = head,slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }

    static Node reverse(Node head){
        Node newHead = null;
        while(head != null){
            Node next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

    static void print(Node head){
        while(head != null){
            System.out.print(head.val+"->");
            head = head.next;
        }
        System.out.println("null");
    }

    static Node merge(Node head1,Node head2){
        if(head1 == null){
            return head2;
        }
        if(head2 == null){
            return head1;
        }
        Node dummy = new Node(-1, null);
        Node curr = dummy;
        while(head1 != null || head2 != null){
            if(head1 == null){
                curr.next = head2;
                head2 = head2.next;
            }else if(head2 == null){
                curr.next = head1;
                head1 = head1.next;
            }else if(head1.val < head2.val){
                curr.next = head1;
                head1 = head1.next;
            }else{
                curr.next = head2;
                head2 = head2.next;
            }
            curr = curr.next;
        }
        return dummy.next;
    }


    public static void main(String[] args) {
        Node node1 = new Node(1, null);
        Node node2 = new Node(2, null);
        Node node3 = new Node(3, null);
        Node node4 = new Node(4, null);
        Node node5 = new Node(5, null);
        Node node6 = new Node(6, null);

        node1.next = node2;
        node2.next = node3;

        node4.next = node5;
        node5.next = node6;

        print(node1);
        print(node4);
        print(merge(node4,node1));
    }
}
