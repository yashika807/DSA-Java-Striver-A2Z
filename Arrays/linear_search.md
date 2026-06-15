# Linear Search

**Difficulty:** Easy  
**Topic:** Arrays / Searching

---

## Problem Statement

Given an array and a target element, return the **index** of the target if found, else return `-1`.

```
Input:  arr[] = [3, 4, 1, 7, 5], target = 7
Output: 3

Input:  arr[] = [3, 4, 1, 7, 5], target = 10
Output: -1
```

---

## Approach — Linear Search (Only Approach)

**Idea:** Traverse the array from left to right. Compare each element with the target. Return the index if found.

> No brute/better split needed here — linear search **is** the fundamental approach for unsorted arrays.

**Time Complexity:** O(N)  
**Space Complexity:** O(1)

```cpp
int linearSearch(vector<int>& arr, int target) {
    int n = arr.size();
    for (int i = 0; i < n; i++) {
        if (arr[i] == target) return i;
    }
    return -1; // not found
}
```

**Dry Run:**
```
arr = [3, 4, 1, 7, 5], target = 7

i=0: arr[0]=3 ≠ 7
i=1: arr[1]=4 ≠ 7
i=2: arr[2]=1 ≠ 7
i=3: arr[3]=7 == 7 → return 3 ✓
```

> **Note:** If the array were **sorted**, Binary Search (O(log N)) would be preferred.
