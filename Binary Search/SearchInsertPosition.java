import java.util.*;

public class SearchInsertPosition {

    // ===================================================
    // Problem: Given sorted array and target,
    // return index of target if found,
    // else return index where it would be inserted to keep sorted order
    // This is exactly the Lower Bound problem
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force
    // Linear scan, return index where arr[i] >= target
    // Time: O(N) | Space: O(1)
    // ===================================================
    public static int searchInsertBrute(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] >= target) return i;
        return arr.length;
    }

    // ===================================================
    // Approach 2 - Optimal (Binary Search / Lower Bound)
    // Find the first position where arr[mid] >= target
    // Time: O(log N) | Space: O(1)
    // ===================================================
    public static int searchInsert(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int ans = arr.length;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] >= target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6};
        System.out.println(searchInsert(arr, 5));  // Output: 2 (found at index 2)
        System.out.println(searchInsert(arr, 2));  // Output: 1 (insert between 1 and 3)
        System.out.println(searchInsert(arr, 7));  // Output: 4 (insert at end)
        System.out.println(searchInsert(arr, 0));  // Output: 0 (insert at start)
    }
}
