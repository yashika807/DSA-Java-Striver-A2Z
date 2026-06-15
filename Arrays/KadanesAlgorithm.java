import java.util.*;

public class KadanesAlgorithm {

    // ===================================================
    // Approach 1 - Brute Force
    // Generate all subarrays, compute sum, track maximum
    // Time: O(N^2) | Space: O(1)
    // ===================================================
    public static int maxSubarraySumBrute(int[] arr) {
        int n = arr.length;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[j];
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    // ===================================================
    // Approach 2 - Optimal (Kadane's Algorithm)
    // If running sum goes negative, reset to 0 (discard prefix)
    // Keep tracking the maximum sum seen so far
    // Time: O(N) | Space: O(1)
    // ===================================================
    public static int maxSubarraySum(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;

        for (int x : arr) {
            currentSum += x;
            maxSum = Math.max(maxSum, currentSum);
            if (currentSum < 0) currentSum = 0; // reset negative prefix
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubarraySum(arr));
        // Output: 6  →  subarray [4, -1, 2, 1]
    }
}
