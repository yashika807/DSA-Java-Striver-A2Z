# 📏 Find the Length of a Linked List

## 🧠 Problem Statement
Given the head of a singly linked list, find the total number of nodes present in the linked list.

---

# 🚀 Approaches

## 🟥 Brute Force Approach

### 💡 Idea
- Start from the head node.
- Traverse the entire linked list.
- Increment a counter for every node visited.
- Return the final count.

### ✅ Algorithm
1. Initialize `count = 0`.
2. Traverse the linked list until `head == null`.
3. Increment `count` for every node.
4. Return `count`.

### ⏱️ Time Complexity
> 🟢 **O(N)**

- Every node is visited exactly once.

### 💾 Space Complexity
> 🟢 **O(1)**

- No extra space is used.

---

## 🟨 Better Approach

> ⚠️ **Not Applicable**

Since every node must be visited to know the exact length, there is no better approach than a single traversal.

---

## 🟩 Optimal Approach

### 💡 Idea
Traverse the linked list only once while maintaining a counter.

### ✅ Algorithm
1. Initialize `count = 0`.
2. Start from the head.
3. Move to the next node until the end.
4. Increment `count` after visiting each node.
5. Return `count`.

### ⏱️ Time Complexity
> 🟢 **O(N)**

### 💾 Space Complexity
> 🟢 **O(1)**

---

# 📊 Complexity Analysis

| Approach | Time Complexity | Space Complexity |
|----------|-----------------|------------------|
| 🟥 Brute Force | **O(N)** | **O(1)** |
| 🟨 Better | **Not Applicable** | **Not Applicable** |
| 🟩 Optimal | **O(N)** | **O(1)** |

---

# 🎯 Key Takeaways

- ✅ Every node must be visited to determine the length.
- ✅ A single traversal is sufficient.
- ✅ No additional memory is required.
- ✅ This is already the most optimal solution.

---
⭐ **Optimal Solution:** One traversal with a counter (**O(N)** Time, **O(1)** Space).
