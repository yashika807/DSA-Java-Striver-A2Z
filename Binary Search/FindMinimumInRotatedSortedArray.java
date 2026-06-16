import java.util.*;

public class FindMinimumInRotatedSortedArray {

    // ===================================================
    // Approach 1 - Brute Force
    // Linear scan for minimum
    // Time: O(N) | Space: O(1)
    // ===================================================
    public static int findMinBrute(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int x : arr) min = Math.min(min, x);
        return min;
    }

    // ===================================================
    // Approach 2 - Optimal (Binary Search)
    // Key insight: minimum is at the rotation point
    // The sorted half always has arr[low] as its minimum
    // → if left half sorted: arr[low] is candidate, move right
    // → if right half sorted: arr[mid] is candidate, move left
    //
    // Time: O(log N) | Space: O(1)
    // ===================================================
    public static int findMin(int[] arr) {
        int low = 0, high = arr.length - 1;
        int ans = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // entire remaining range is sorted → arr[low] is min of this half
            if (arr[low] <= arr[high]) {
                ans = Math.min(ans, arr[low]);
                break;
            }

            // left half is sorted → arr[low] is min of left half
            if (arr[low] <= arr[mid]) {
                ans = Math.min(ans, arr[low]);
                low = mid + 1; // discard left, look in right
            }
            // right half is sorted → arr[mid] is min of right half
            else {
                ans = Math.min(ans, arr[mid]);
                high = mid - 1; // discard right, look in left
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 5, 1, 2};
        System.out.println(findMin(arr));  // Output: 1

        int[] arr2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(findMin(arr2)); // Output: 0
    }
}
