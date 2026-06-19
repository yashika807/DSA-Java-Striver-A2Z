// Solution class to handle linked list operations
class Solution {
    // Function to insert a new node at the head
    public Node insertAtHead(Node head, int newData) {
        // Create a new node whose next points to current head
        Node newNode = new Node(newData, head);
        // Return the new node as the head
        return newNode;
    }

    // Function to print the linked list
    public void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}
