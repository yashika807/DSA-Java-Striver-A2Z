import java.util.*;

public class SplitArrayLargestSum {

    // ===================================================
    // Problem: Split array into K subarrays
    // Minimize the maximum sum among all subarrays
    // (Same pattern as Book Allocation)
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force
    // Try every possible max sum limit from max(arr) to sum(arr)
    // Return first limit where K splits are sufficient
    // Time: O((sum - max) * N) | Space: O(1)
    // ===================================================
    public static int splitArrayBrute(int[] arr, int k) {
        int low = Arrays.stream(arr).max().getAsInt();
        int high = Arrays.stream(arr).sum();
        for (int limit = low; limit <= high; limit++) {
            if (countSplits(arr, limit) <= k) return limit;
        }
        return high;
    }

    // ===================================================
    // Approach 2 - Optimal (Binary Search on Answer)
    // Search space: [max(arr), sum(arr)]
    // If splits needed with limit=mid <= k → valid, try smaller (go left)
    // Else → go right
    // Time: O(N * log(sum - max)) | Space: O(1)
    // ===================================================
    private static int countSplits(int[] arr, int maxSum) {
        int pieces = 1, currentSum = 0;
        for (int x : arr) {
            if (currentSum + x > maxSum) {
                pieces++;
                currentSum = 0;
            }
            currentSum += x;
        }
        return pieces;
    }

    public static int splitArray(int[] arr, int k) {
        int low = Arrays.stream(arr).max().getAsInt();
        int high = Arrays.stream(arr).sum();
        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (countSplits(arr, mid) <= k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {7, 2, 5, 10, 8};
        System.out.println(splitArray(arr, 2));  // Output: 18
    }
}
