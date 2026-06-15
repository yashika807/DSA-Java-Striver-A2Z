# Left Rotate Array by K Places

**Difficulty:** Easy  
**Topic:** Arrays

---

## Problem Statement

Given an array of size N, left rotate it by K positions.

```
Input:  arr[] = [1, 2, 3, 4, 5], K = 2
Output: arr[] = [3, 4, 5, 1, 2]
```

---

## Approach 1 — Brute Force

**Idea:** Perform left-rotate-by-one, K times.

**Time Complexity:** O(N × K)  
**Space Complexity:** O(1)

```cpp
void leftRotateByK_Brute(vector<int>& arr, int k) {
    int n = arr.size();
    k = k % n; // handle k >= n
    for (int i = 0; i < k; i++) {
        int temp = arr[0];
        for (int j = 0; j < n - 1; j++) {
            arr[j] = arr[j + 1];
        }
        arr[n - 1] = temp;
    }
}
```

---

## Approach 2 — Better (Using Extra Array)

**Idea:** Copy the first K elements to a temp array. Shift the rest to the front. Put the temp elements at the end.

**Time Complexity:** O(N)  
**Space Complexity:** O(K)

```cpp
void leftRotateByK_Better(vector<int>& arr, int k) {
    int n = arr.size();
    k = k % n;
    vector<int> temp(arr.begin(), arr.begin() + k); // store first k

    for (int i = k; i < n; i++) {
        arr[i - k] = arr[i];                         // shift left
    }
    for (int i = 0; i < k; i++) {
        arr[n - k + i] = temp[i];                    // fill end
    }
}
```

---

## Approach 3 — Optimal (Reversal Algorithm)

**Idea:** Use the **reverse trick** — no extra space needed.

**Steps:**
1. Reverse first K elements
2. Reverse remaining N-K elements
3. Reverse the whole array

```
arr = [1, 2, 3, 4, 5], k = 2

Step 1 → Reverse [1,2]     → [2, 1, 3, 4, 5]
Step 2 → Reverse [3,4,5]   → [2, 1, 5, 4, 3]
Step 3 → Reverse all       → [3, 4, 5, 1, 2] ✓
```

**Time Complexity:** O(N)  
**Space Complexity:** O(1)

```cpp
void reverse(vector<int>& arr, int l, int r) {
    while (l < r) {
        swap(arr[l++], arr[r--]);
    }
}

void leftRotateByK(vector<int>& arr, int k) {
    int n = arr.size();
    k = k % n; // handle k >= n
    reverse(arr, 0, k - 1);       // step 1
    reverse(arr, k, n - 1);       // step 2
    reverse(arr, 0, n - 1);       // step 3
}
```
