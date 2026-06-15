# Remove Duplicates from Sorted Array

**Difficulty:** Easy  
**Topic:** Arrays

---

## Problem Statement

Given a sorted array, remove the duplicate elements **in-place** such that each element appears only once. Return the number of unique elements (k).

```
Input:  arr[] = [1, 1, 2, 2, 3, 3]
Output: k = 3, arr = [1, 2, 3, ...]
```

---

## Approach 1 — Brute Force

**Idea:** Use a `set` to store unique elements, then copy them back into the array.

- Insert all elements into a `set` (automatically removes duplicates).
- Copy set elements back to the array from index 0.

**Time Complexity:** O(N log N) — set insertions  
**Space Complexity:** O(N) — for the set

```cpp
int removeDuplicates(vector<int>& arr) {
    set<int> st;
    for (int x : arr) st.insert(x);

    int k = 0;
    for (int x : st) arr[k++] = x;

    return k;
}
```

---

## Approach 2 — Optimal (Two Pointers)

**Idea:** Since the array is **sorted**, duplicates are always adjacent. Use two pointers `i` and `j`:
- `i` → points to the last unique element placed
- `j` → scans forward looking for a new unique element
- Whenever `arr[j] != arr[i]`, we found a new unique → place it at `arr[i+1]`

**Time Complexity:** O(N)  
**Space Complexity:** O(1)

```cpp
int removeDuplicates(vector<int>& arr) {
    int i = 0;
    for (int j = 1; j < arr.size(); j++) {
        if (arr[j] != arr[i]) {
            i++;
            arr[i] = arr[j];
        }
    }
    return i + 1; // number of unique elements
}
```

**Dry Run:**
```
arr = [1, 1, 2, 2, 3]
i=0, j=1 → arr[1]==arr[0], skip
i=0, j=2 → arr[2]!=arr[0] → i=1, arr[1]=2
i=1, j=3 → arr[3]==arr[1], skip
i=1, j=4 → arr[4]!=arr[1] → i=2, arr[2]=3
return i+1 = 3
arr = [1, 2, 3, ...]
```
