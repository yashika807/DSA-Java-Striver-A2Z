import java.util.*;

public class SingleElementInSortedArray {

    // ===================================================
    // Approach 1 - Brute Force (XOR)
    // XOR all elements — pairs cancel, single remains
    // Time: O(N) | Space: O(1)
    // ===================================================
    public static int singleNonDuplicateBrute(int[] arr) {
        int xor = 0;
        for (int x : arr) xor ^= x;
        return xor;
    }

    // ===================================================
    // Approach 2 - Optimal (Binary Search)
    // Key insight: before the single element, pairs are at (even, odd) indices
    //              after  the single element, pairs are at (odd, even) indices
    //
    // For mid at even index: if arr[mid] == arr[mid+1] → single is in right half
    // For mid at odd  index: if arr[mid] == arr[mid-1] → single is in right half
    // Otherwise single is in left half (or at mid)
    //
    // Always search on even indices → if mid is odd, do mid--
    //
    // Time: O(log N) | Space: O(1)
    // ===================================================
    public static int singleNonDuplicate(int[] arr) {
        int n = arr.length;
        if (n == 1) return arr[0];
        if (arr[0] != arr[1]) return arr[0];
        if (arr[n - 1] != arr[n - 2]) return arr[n - 1];

        int low = 1, high = n - 2;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] != arr[mid - 1] && arr[mid] != arr[mid + 1])
                return arr[mid]; // found the single element

            // ensure mid is even
            if (mid % 2 == 1) mid--;

            // if pair is (mid, mid+1) → single is in right half
            if (arr[mid] == arr[mid + 1]) low = mid + 2;
            else high = mid - 1;             // single is in left half
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3, 3, 4, 4, 8, 8};
        System.out.println(singleNonDuplicate(arr)); // Output: 2

        int[] arr2 = {3, 3, 7, 7, 10, 11, 11};
        System.out.println(singleNonDuplicate(arr2)); // Output: 10
    }
}
