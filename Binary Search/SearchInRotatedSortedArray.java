import java.util.*;

public class SearchInRotatedSortedArray {

    // ===================================================
    // Approach 1 - Brute Force
    // Linear search through the rotated array
    // Time: O(N) | Space: O(1)
    // ===================================================
    public static int searchBrute(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == target) return i;
        return -1;
    }

    // ===================================================
    // Approach 2 - Optimal (Modified Binary Search)
    // Key insight: one half of a rotated array is always sorted
    // Check which half is sorted, then decide which half target lies in
    //
    // If left half sorted  (arr[low] <= arr[mid]):
    //   → if target in [arr[low], arr[mid]] → search left
    //   → else search right
    // If right half sorted (arr[mid] <= arr[high]):
    //   → if target in [arr[mid], arr[high]] → search right
    //   → else search left
    //
    // Time: O(log N) | Space: O(1)
    // ===================================================
    public static int search(int[] arr, int target) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) return mid;

            // left half is sorted
            if (arr[low] <= arr[mid]) {
                if (arr[low] <= target && target < arr[mid])
                    high = mid - 1;   // target in left half
                else
                    low = mid + 1;    // target in right half
            }
            // right half is sorted
            else {
                if (arr[mid] < target && target <= arr[high])
                    low = mid + 1;    // target in right half
                else
                    high = mid - 1;   // target in left half
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(search(arr, 0));   // Output: 4
        System.out.println(search(arr, 3));   // Output: -1
    }
}
