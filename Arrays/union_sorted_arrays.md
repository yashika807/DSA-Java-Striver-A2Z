# Union of Two Sorted Arrays

**Difficulty:** Easy  
**Topic:** Arrays / Two Pointers

---

## Problem Statement

Given two sorted arrays, return their **union** — all distinct elements from both arrays in sorted order.

```
Input:  a[] = [1, 1, 2, 3, 4], b[] = [2, 3, 4, 4, 5]
Output: [1, 2, 3, 4, 5]
```

---

## Approach 1 — Brute Force

**Idea:** Insert all elements from both arrays into a `set`. Since a set stores unique elements in sorted order, just copy it to the result.

**Time Complexity:** O((N+M) log(N+M))  
**Space Complexity:** O(N+M)

```cpp
vector<int> unionBrute(vector<int>& a, vector<int>& b) {
    set<int> st;
    for (int x : a) st.insert(x);
    for (int x : b) st.insert(x);
    return vector<int>(st.begin(), st.end());
}
```

---

## Approach 2 — Optimal (Two Pointers)

**Idea:** Both arrays are sorted — use two pointers `i` and `j` to merge them like in merge sort, but skip duplicates.

**Steps:**
- Compare `a[i]` and `b[j]`
- Pick the smaller one; if it's not a duplicate of the last added element, add to result
- If equal, pick either and advance both pointers
- Handle remaining elements in either array

**Time Complexity:** O(N + M)  
**Space Complexity:** O(N + M) — for the result array

```cpp
vector<int> unionArrays(vector<int>& a, vector<int>& b) {
    int n = a.size(), m = b.size();
    int i = 0, j = 0;
    vector<int> result;

    while (i < n && j < m) {
        if (a[i] <= b[j]) {
            // add a[i] if not duplicate
            if (result.empty() || result.back() != a[i])
                result.push_back(a[i]);
            i++;
        } else {
            // add b[j] if not duplicate
            if (result.empty() || result.back() != b[j])
                result.push_back(b[j]);
            j++;
        }
    }

    // remaining elements of a
    while (i < n) {
        if (result.empty() || result.back() != a[i])
            result.push_back(a[i]);
        i++;
    }

    // remaining elements of b
    while (j < m) {
        if (result.empty() || result.back() != b[j])
            result.push_back(b[j]);
        j++;
    }

    return result;
}
```

**Dry Run:**
```
a = [1, 1, 2, 3],  b = [2, 3, 4]
i=0, j=0: a[0]=1 < b[0]=2 → add 1, i=1
i=1, j=0: a[1]=1 == last(1), skip, i=2
i=2, j=0: a[2]=2 == b[0]=2 → add 2, i=3
i=3, j=1: a[3]=3 == b[1]=3 → add 3, i=4
i=4 (done), j=2: add 4
Result: [1, 2, 3, 4] ✓
```
