# Find Missing Number

**Difficulty:** Easy  
**Topic:** Arrays / Math / Bit Manipulation

---

## Problem Statement

Given an array of size N containing numbers from 1 to N+1 with exactly **one number missing**, find the missing number.

```
Input:  arr[] = [1, 2, 4, 5], N = 5
Output: 3
```

---

## Approach 1 — Brute Force

**Idea:** For each number from 1 to N+1, check if it exists in the array using linear search.

**Time Complexity:** O(N²)  
**Space Complexity:** O(1)

```cpp
int missingNumber_Brute(vector<int>& arr, int n) {
    for (int i = 1; i <= n; i++) {
        bool found = false;
        for (int j = 0; j < arr.size(); j++) {
            if (arr[j] == i) { found = true; break; }
        }
        if (!found) return i;
    }
    return -1;
}
```

---

## Approach 2 — Better (Hashing)

**Idea:** Use a hash array of size N+2. Mark visited numbers. The index not marked is the missing number.

**Time Complexity:** O(N)  
**Space Complexity:** O(N)

```cpp
int missingNumber_Better(vector<int>& arr, int n) {
    vector<int> hash(n + 2, 0);
    for (int x : arr) hash[x] = 1;

    for (int i = 1; i <= n; i++) {
        if (hash[i] == 0) return i;
    }
    return -1;
}
```

---

## Approach 3 — Optimal 1 (Summation Formula)

**Idea:** Sum of 1 to N = N*(N+1)/2. Subtract actual sum of array from expected sum.

**Time Complexity:** O(N)  
**Space Complexity:** O(1)

```cpp
int missingNumber_Sum(vector<int>& arr, int n) {
    int expectedSum = n * (n + 1) / 2;
    int actualSum = 0;
    for (int x : arr) actualSum += x;
    return expectedSum - actualSum;
}
```

---

## Approach 4 — Optimal 2 (XOR)

**Idea:** XOR all numbers 1 to N, then XOR with all array elements. Duplicates cancel out; what remains is the missing number.

> Preferred when N is very large (avoids integer overflow from summation).

**Time Complexity:** O(N)  
**Space Complexity:** O(1)

```cpp
int missingNumber_XOR(vector<int>& arr, int n) {
    int xor1 = 0, xor2 = 0;

    for (int i = 1; i <= n; i++) xor1 ^= i;        // XOR 1 to n
    for (int x : arr) xor2 ^= x;                    // XOR array

    return xor1 ^ xor2; // missing number
}
```

**Dry Run:**
```
arr = [1, 2, 4, 5], n = 5
xor1 = 1^2^3^4^5
xor2 = 1^2^4^5
xor1 ^ xor2 = 3 ✓ (all pairs cancel, only 3 remains)
```
