import java.util.*;

public class SmallestDivisor {

    // ===================================================
    // Problem: Find smallest divisor such that
    // sum of ceil(arr[i] / divisor) <= threshold
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force
    // Try every divisor from 1 to max(arr)
    // Return first valid divisor
    // Time: O(max(arr) * N) | Space: O(1)
    // ===================================================
    public static int smallestDivisorBrute(int[] arr, int threshold) {
        int maxVal = Arrays.stream(arr).max().getAsInt();
        for (int d = 1; d <= maxVal; d++) {
            if (computeSum(arr, d) <= threshold) return d;
        }
        return maxVal;
    }

    // ===================================================
    // Approach 2 - Optimal (Binary Search on Answer)
    // Search space: [1, max(arr)]
    // As divisor increases → sum decreases (monotonic)
    // If sum <= threshold → valid, try smaller divisor (go left)
    // Else → go right
    // Time: O(N * log(max(arr))) | Space: O(1)
    // ===================================================
    private static int computeSum(int[] arr, int divisor) {
        int sum = 0;
        for (int x : arr) sum += (x + divisor - 1) / divisor; // ceil division
        return sum;
    }

    public static int smallestDivisor(int[] arr, int threshold) {
        int low = 1, high = Arrays.stream(arr).max().getAsInt();
        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (computeSum(arr, mid) <= threshold) {
                ans = mid;
                high = mid - 1; // try smaller divisor
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 9};
        System.out.println(smallestDivisor(arr, 6));  // Output: 5
    }
}
