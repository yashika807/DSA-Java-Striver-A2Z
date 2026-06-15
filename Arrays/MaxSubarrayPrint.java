import java.util.*;

public class MaxSubarrayPrint {

    // ===================================================
    // Approach 1 - Brute Force
    // Generate all subarrays, track the one with max sum
    // Time: O(N^2) | Space: O(1)
    // ===================================================
    public static void maxSubarrayBrute(int[] arr) {
        int n = arr.length;
        int maxSum = Integer.MIN_VALUE;
        int start = 0, end = 0;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[j];
                if (sum > maxSum) {
                    maxSum = sum;
                    start = i;
                    end = j;
                }
            }
        }
        System.out.print("Max Sum: " + maxSum + " | Subarray: ");
        for (int i = start; i <= end; i++) System.out.print(arr[i] + " ");
        System.out.println();
    }

    // ===================================================
    // Approach 2 - Optimal (Kadane's + Index Tracking)
    // Extend Kadane's algorithm to also track start and end indices
    // When currentSum < 0, reset and mark new potential start
    // Time: O(N) | Space: O(1)
    // ===================================================
    public static void maxSubarray(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;
        int start = 0, ansStart = 0, ansEnd = 0;

        for (int i = 0; i < arr.length; i++) {
            if (currentSum == 0) start = i; // mark new window start

            currentSum += arr[i];

            if (currentSum > maxSum) {
                maxSum = currentSum;
                ansStart = start;
                ansEnd = i;
            }

            if (currentSum < 0) currentSum = 0; // reset negative prefix
        }

        System.out.print("Max Sum: " + maxSum + " | Subarray: ");
        for (int i = ansStart; i <= ansEnd; i++) System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        maxSubarray(arr);
        // Output: Max Sum: 6 | Subarray: 4 -1 2 1
    }
}
