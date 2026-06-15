# Left Rotate Array by One

**Difficulty:** Easy  
**Topic:** Arrays

---

## Problem Statement

Given an array, left rotate it by **one position**. The first element goes to the last position.

```
Input:  arr[] = [1, 2, 3, 4, 5]
Output: arr[] = [2, 3, 4, 5, 1]
```

---

## Approach 1 — Brute Force

**Idea:** Store the first element in a temp variable. Shift all elements one step to the left. Place temp at the last index.

**Time Complexity:** O(N)  
**Space Complexity:** O(1)

```cpp
void leftRotateByOne(vector<int>& arr) {
    int n = arr.size();
    int temp = arr[0];                   // store first element
    for (int i = 0; i < n - 1; i++) {
        arr[i] = arr[i + 1];             // shift left
    }
    arr[n - 1] = temp;                   // place first at last
}
```

**Dry Run:**
```
arr = [1, 2, 3, 4, 5]
temp = 1
shift → [2, 3, 4, 5, 5]
arr[4] = 1 → [2, 3, 4, 5, 1]
```

> **Note:** This is already the optimal solution for a single rotation.  
> **Time Complexity:** O(N) | **Space Complexity:** O(1)
