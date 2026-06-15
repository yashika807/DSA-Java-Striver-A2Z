# Find the Number That Appears Once (Others Appear Twice)

**Difficulty:** Medium  
**Topic:** Arrays / Bit Manipulation

---

## Problem Statement

Given an array where every element appears **twice** except for one element which appears **once**, find that element.

```
Input:  arr[] = [4, 1, 2, 1, 2]
Output: 4
```

---

## Approach 1 — Brute Force

**Idea:** For each element, count its frequency using a nested loop. Return the one with frequency 1.

**Time Complexity:** O(N²)  
**Space Complexity:** O(1)

```cpp
int singleNumber_Brute(vector<int>& arr) {
    int n = arr.size();
    for (int i = 0; i < n; i++) {
        int count = 0;
        for (int j = 0; j < n; j++) {
            if (arr[j] == arr[i]) count++;
        }
        if (count == 1) return arr[i];
    }
    return -1;
}
```

---

## Approach 2 — Better (Hashing)

**Idea:** Use a hash map to count frequencies. Return the element with frequency 1.

**Time Complexity:** O(N)  
**Space Complexity:** O(N)

```cpp
int singleNumber_Better(vector<int>& arr) {
    unordered_map<int, int> freq;
    for (int x : arr) freq[x]++;
    for (auto& [val, cnt] : freq) {
        if (cnt == 1) return val;
    }
    return -1;
}
```

---

## Approach 3 — Optimal (XOR)

**Idea:** XOR has two key properties:
- `a ^ a = 0` (same numbers cancel)
- `a ^ 0 = a` (anything XOR 0 is itself)

XOR all elements together — every pair cancels out, and only the single element remains.

**Time Complexity:** O(N)  
**Space Complexity:** O(1)

```cpp
int singleNumber(vector<int>& arr) {
    int xorVal = 0;
    for (int x : arr) xorVal ^= x;
    return xorVal;
}
```

**Dry Run:**
```
arr = [4, 1, 2, 1, 2]
xorVal = 0
^ 4 → 4
^ 1 → 5
^ 2 → 7
^ 1 → 6  (1 cancels with 1)
^ 2 → 4  (2 cancels with 2)

Output: 4 ✓
```
