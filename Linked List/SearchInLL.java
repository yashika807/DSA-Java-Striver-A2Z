# Search in a Linked List (GeeksforGeeks)

## Problem Statement
Given the head of a singly linked list and an integer `key`, determine whether the key is present in the linked list. Return `true` if the key exists; otherwise, return `false`.

---

## Approach
- Traverse the linked list from the head.
- Compare each node's data with the given `key`.
- If a matching node is found, return `true`.
- If the end of the list is reached without finding the key, return `false`.

---

## Java Solution

```java
class Solution {
    static boolean searchKey(int n, Node head, int key) {
        Node temp = head;

        while (temp != null) {
            if (temp.data == key) {
                return true;
            }
            temp = temp.next;
        }

        return false;
    }
}
```

---

## Algorithm
1. Initialize a pointer `temp` to the head of the linked list.
2. Traverse the list until `temp` becomes `null`.
3. If `temp.data` equals the given `key`, return `true`.
4. Otherwise, move to the next node.
5. If the traversal completes without finding the key, return `false`.

---

## Time Complexity
- **O(n)**, where `n` is the number of nodes.

## Space Complexity
- **O(1)**

---

## Concepts Used
- Singly Linked List
- Linear Traversal
- Iteration

---

## Status
✅ Solved using Java
