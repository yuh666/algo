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


    public static void main(String[] args) {
        Node node = new Node(1, new Node(2, new Node(3, null)));
        print(node);
        node = reverse(node);
        print(node);
    }
}
