import java.util.*;

public class LowerBound {

    // ===================================================
    // Lower Bound Definition:
    // Smallest index i such that arr[i] >= target
    // If no such index exists, return arr.length
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force
    // Linear scan, return first index where arr[i] >= target
    // Time: O(N) | Space: O(1)
    // ===================================================
    public static int lowerBoundBrute(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] >= target) return i;
        return arr.length;
    }

    // ===================================================
    // Approach 2 - Optimal (Binary Search)
    // Whenever arr[mid] >= target, it's a potential answer → go left
    // Whenever arr[mid] < target → go right
    // Time: O(log N) | Space: O(1)
    // ===================================================
    public static int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int ans = arr.length; // default if target > all elements

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] >= target) {
                ans = mid;      // potential answer, try to go smaller
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 3, 5};
        System.out.println(lowerBound(arr, 2));  // Output: 1
        System.out.println(lowerBound(arr, 4));  // Output: 5  (arr[5]=5 >= 4)
        System.out.println(lowerBound(arr, 6));  // Output: 6  (not found)
    }
}
