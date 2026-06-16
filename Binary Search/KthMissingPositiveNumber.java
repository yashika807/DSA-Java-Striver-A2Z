import java.util.*;

public class KthMissingPositiveNumber {

    // ===================================================
    // Approach 1 - Brute Force
    // Count missing numbers from 1 upward until kth missing found
    // Time: O(N + K) | Space: O(1)
    // ===================================================
    public static int findKthMissingBrute(int[] arr, int k) {
        for (int i = 1; ; i++) {
            boolean found = false;
            for (int x : arr) if (x == i) { found = true; break; }
            if (!found) { k--; if (k == 0) return i; }
        }
    }

    // ===================================================
    // Approach 2 - Optimal (Binary Search on Index)
    // Key insight: at index i (0-based), if array had no missing numbers,
    // arr[i] would be i+1. So missing count before arr[i] = arr[i] - (i+1)
    //
    // Binary search for the largest index where missing count < k
    // Answer = arr[high] + (k - missing at high)  → or simply: low + k
    //
    // Time: O(log N) | Space: O(1)
    // ===================================================
    public static int findKthMissing(int[] arr, int k) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int missing = arr[mid] - (mid + 1); // how many missing before arr[mid]

            if (missing < k)
                low = mid + 1;
            else
                high = mid - 1;
        }
        // After loop: high is the last index where missing < k
        // Answer lies after arr[high], specifically at position: arr[high] + (k - missing_at_high)
        // Simplified: low + k  (since low = high + 1)
        return low + k;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 7, 11};
        System.out.println(findKthMissing(arr, 5));  // Output: 9
        System.out.println(findKthMissing(arr, 1));  // Output: 1
    }
}
