class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {

    // Delete the head node
    public static Node deleteHead(Node head) {

        // Empty list
        if (head == null) {
            return null;
        }

        // Move head to the next node
        head = head.next;

        return head;
    }
}
Time Complexity
O(1)
Space Complexity
O(1)
