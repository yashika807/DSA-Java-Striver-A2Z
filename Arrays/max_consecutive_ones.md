# Maximum Consecutive Ones

**Difficulty:** Easy  
**Topic:** Arrays

---

## Problem Statement

Given a binary array (only 0s and 1s), find the maximum number of **consecutive 1s**.

```
Input:  arr[] = [1, 1, 0, 1, 1, 1, 0, 1]
Output: 3
```

---

## Approach — Optimal (Single Pass)

**Idea:** Traverse the array. Keep a running count of consecutive 1s. Reset to 0 on seeing a 0. Track the maximum count throughout.

> No brute/better needed — a single pass is already optimal.

**Time Complexity:** O(N)  
**Space Complexity:** O(1)

```cpp
int maxConsecutiveOnes(vector<int>& arr) {
    int maxCount = 0, count = 0;
    for (int x : arr) {
        if (x == 1) {
            count++;
            maxCount = max(maxCount, count);
        } else {
            count = 0; // reset on 0
        }
    }
    return maxCount;
}
```

**Dry Run:**
```
arr = [1, 1, 0, 1, 1, 1, 0, 1]

x=1 → count=1, max=1
x=1 → count=2, max=2
x=0 → count=0
x=1 → count=1, max=2
x=1 → count=2, max=2
x=1 → count=3, max=3
x=0 → count=0
x=1 → count=1, max=3

Output: 3 ✓
```
