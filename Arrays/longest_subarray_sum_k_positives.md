# Longest Subarray with Given Sum K (Positives Only)

**Difficulty:** Medium  
**Topic:** Arrays / Sliding Window / Prefix Sum

---

## Problem Statement

Given an array of **positive integers** and a target sum K, find the **length of the longest subarray** whose sum equals K.

```
Input:  arr[] = [1, 2, 3, 1, 1, 1, 1, 4, 2, 3], K = 3
Output: 3  (subarray [1, 1, 1])
```

---

## Approach 1 — Brute Force

**Idea:** Generate all subarrays, compute their sum, track the maximum length where sum == K.

**Time Complexity:** O(N³)  
**Space Complexity:** O(1)

```cpp
int longestSubarrayK_Brute(vector<int>& arr, int k) {
    int n = arr.size(), maxLen = 0;
    for (int i = 0; i < n; i++) {
        for (int j = i; j < n; j++) {
            int sum = 0;
            for (int l = i; l <= j; l++) sum += arr[l]; // O(N) inner
            if (sum == k) maxLen = max(maxLen, j - i + 1);
        }
    }
    return maxLen;
}
```

---

## Approach 2 — Better (Prefix Sum + HashMap)

**Idea:** Use prefix sums. For each index `j`, if `prefixSum - K` was seen at some earlier index `i`, then subarray `[i+1, j]` has sum K. Use a map to store the first occurrence of each prefix sum.

> This works for arrays with **any integers** (positives, negatives, zeros).

**Time Complexity:** O(N)  
**Space Complexity:** O(N)

```cpp
int longestSubarrayK_Better(vector<int>& arr, int k) {
    unordered_map<int, int> prefixMap; // prefixSum → first index
    int sum = 0, maxLen = 0;

    for (int i = 0; i < arr.size(); i++) {
        sum += arr[i];

        if (sum == k) maxLen = max(maxLen, i + 1);

        if (prefixMap.find(sum - k) != prefixMap.end()) {
            maxLen = max(maxLen, i - prefixMap[sum - k]);
        }

        if (prefixMap.find(sum) == prefixMap.end()) {
            prefixMap[sum] = i; // store first occurrence only
        }
    }
    return maxLen;
}
```

---

## Approach 3 — Optimal (Sliding Window)

**Idea:** Since all elements are **positive**, we can use the sliding window technique:
- Expand the window by moving `right`
- If `sum > K`, shrink from the left by moving `left`
- If `sum == K`, update max length

> Works **only** for arrays with positive integers (no zeros/negatives), because shrinking the window always decreases the sum.

**Time Complexity:** O(N)  
**Space Complexity:** O(1)

```cpp
int longestSubarrayK(vector<int>& arr, int k) {
    int n = arr.size();
    int left = 0, right = 0;
    int sum = 0, maxLen = 0;

    while (right < n) {
        sum += arr[right]; // expand right

        while (sum > k && left <= right) {
            sum -= arr[left]; // shrink left
            left++;
        }

        if (sum == k) {
            maxLen = max(maxLen, right - left + 1);
        }

        right++;
    }
    return maxLen;
}
```

**Dry Run:**
```
arr = [1, 2, 3, 1, 1, 1], K = 3

r=0: sum=1
r=1: sum=3 == K → len=2, maxLen=2
r=2: sum=6 > K → shrink: sum=5(l=1), sum=3(l=2) == K → len=1, maxLen=2
r=3: sum=4 > K → shrink: sum=1(l=3)
r=4: sum=2
r=5: sum=3 == K → len=3, maxLen=3

Output: 3 ✓
```
