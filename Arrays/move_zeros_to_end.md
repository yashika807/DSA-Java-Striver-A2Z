# Move Zeros to End

**Difficulty:** Easy  
**Topic:** Arrays

---

## Problem Statement

Given an array, move all the zeros to the end while maintaining the **relative order** of non-zero elements.

```
Input:  arr[] = [1, 0, 2, 3, 0, 4, 0, 1]
Output: arr[] = [1, 2, 3, 4, 1, 0, 0, 0]
```

---

## Approach 1 — Brute Force

**Idea:** Collect all non-zero elements in a temp array. Copy them back. Fill remaining spots with zeros.

**Time Complexity:** O(N)  
**Space Complexity:** O(N)

```cpp
void moveZeros_Brute(vector<int>& arr) {
    vector<int> temp;
    for (int x : arr) {
        if (x != 0) temp.push_back(x);  // collect non-zeros
    }
    int n = arr.size(), k = temp.size();
    for (int i = 0; i < k; i++) arr[i] = temp[i];   // fill non-zeros
    for (int i = k; i < n; i++) arr[i] = 0;          // fill zeros
}
```

---

## Approach 2 — Optimal (Two Pointers)

**Idea:** Use two pointers:
- `j` → find the **first zero** in the array
- `i` → scan from `j+1`, whenever a non-zero is found, swap with `arr[j]` and advance `j`

**Time Complexity:** O(N)  
**Space Complexity:** O(1)

```cpp
void moveZeros(vector<int>& arr) {
    int n = arr.size();

    int j = -1;
    // find the first zero
    for (int i = 0; i < n; i++) {
        if (arr[i] == 0) { j = i; break; }
    }

    if (j == -1) return; // no zeros found

    for (int i = j + 1; i < n; i++) {
        if (arr[i] != 0) {
            swap(arr[i], arr[j]); // bring non-zero forward
            j++;
        }
    }
}
```

**Dry Run:**
```
arr = [1, 0, 2, 3, 0, 4]
j = 1 (first zero)

i=2: arr[2]=2 ≠ 0 → swap(arr[2], arr[1]) → [1,2,0,3,0,4], j=2
i=3: arr[3]=3 ≠ 0 → swap(arr[3], arr[2]) → [1,2,3,0,0,4], j=3
i=4: arr[4]=0     → skip
i=5: arr[5]=4 ≠ 0 → swap(arr[5], arr[3]) → [1,2,3,4,0,0], j=4

Result: [1, 2, 3, 4, 0, 0] ✓
```
