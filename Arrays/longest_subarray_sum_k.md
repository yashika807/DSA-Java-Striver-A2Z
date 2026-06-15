# Longest Subarray with Sum K (Positives + Negatives + Zeros)

**Difficulty:** Medium  
**Topic:** Arrays / Prefix Sum / HashMap

---

## Problem Statement

Given an array that may contain **positive numbers, negative numbers, and zeros**, find the **length of the longest subarray** whose sum equals K.

```
Input:  arr[] = [2, 0, 0, 3], K = 3
Output: 4  (entire array sums to 3, with zeros extending it)

Input:  arr[] = [10, 5, 2, 7, 1, -10], K = 15
Output: 6
```

> **Note:** The Sliding Window approach from the previous problem does NOT work here because shrinking the window doesn't guarantee a decrease in sum when negatives/zeros are present.

---

## Approach 1 — Brute Force

**Idea:** Check all subarrays, compute their sum, track the maximum length where sum == K.

**Time Complexity:** O(N²)  
**Space Complexity:** O(1)

```cpp
int longestSubarrayK_Brute(vector<int>& arr, int k) {
    int n = arr.size(), maxLen = 0;
    for (int i = 0; i < n; i++) {
        int sum = 0;
        for (int j = i; j < n; j++) {
            sum += arr[j];
            if (sum == k) maxLen = max(maxLen, j - i + 1);
        }
    }
    return maxLen;
}
```

---

## Approach 2 — Optimal (Prefix Sum + HashMap)

**Idea:**

Let `prefixSum[i]` = sum of `arr[0..i]`.

If `prefixSum[j] - prefixSum[i] = K`, then subarray `arr[i+1..j]` has sum K.  
→ We need `prefixSum[i] = prefixSum[j] - K`

Use a hashmap to store the **first occurrence** of each prefix sum.

**Key Observations:**
- Store `prefixMap[sum] = i` only if `sum` is **not already in the map** (we want the leftmost index to maximize length).
- If `prefixSum == K` at index `j`, the subarray `arr[0..j]` itself has sum K → length = `j + 1`.

**Time Complexity:** O(N)  
**Space Complexity:** O(N)

```cpp
int longestSubarrayK(vector<int>& arr, int k) {
    unordered_map<int, int> prefixMap; // prefixSum → earliest index
    int sum = 0, maxLen = 0;

    for (int i = 0; i < arr.size(); i++) {
        sum += arr[i];

        // subarray from index 0 to i has sum == k
        if (sum == k) maxLen = max(maxLen, i + 1);

        // check if (sum - k) was seen before
        if (prefixMap.find(sum - k) != prefixMap.end()) {
            int len = i - prefixMap[sum - k];
            maxLen = max(maxLen, len);
        }

        // store first occurrence only (to maximize subarray length)
        if (prefixMap.find(sum) == prefixMap.end()) {
            prefixMap[sum] = i;
        }
    }
    return maxLen;
}
```

**Dry Run:**
```
arr = [10, 5, 2, 7, 1, -10], K = 15

i=0: sum=10, map={10:0}
i=1: sum=15, sum==K → len=2, map={10:0, 15:1}
i=2: sum=17, sum-K=2, not in map, map={..., 17:2}
i=3: sum=24, sum-K=9, not in map, map={..., 24:3}
i=4: sum=25, sum-K=10, found at 0 → len=4-0=4, maxLen=4
i=5: sum=15, sum==K → len=6, maxLen=6

Output: 6 ✓
```

---

## Summary: When to Use Which Approach

| Constraint              | Best Approach       | Time  | Space |
|------------------------|---------------------|-------|-------|
| Only positive integers  | Sliding Window      | O(N)  | O(1)  |
| Positives + Negatives + Zeros | Prefix Sum + HashMap | O(N) | O(N) |
