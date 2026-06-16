import java.util.*;

public class SearchInRotatedSortedArrayII {

    // ===================================================
    // Approach 1 - Brute Force
    // Linear search (duplicates don't affect linear scan)
    // Time: O(N) | Space: O(1)
    // ===================================================
    public static boolean searchBrute(int[] arr, int target) {
        for (int x : arr) if (x == target) return true;
        return false;
    }

    // ===================================================
    // Approach 2 - Optimal (Modified Binary Search with duplicate handling)
    // Same as Rotated Array I, but duplicates can make arr[low]==arr[mid]==arr[high]
    // In that case we can't determine which half is sorted
    // → Shrink both pointers (low++, high--) to skip duplicates
    //
    // Worst case (all duplicates): O(N) | Average: O(log N) | Space: O(1)
    // ===================================================
    public static boolean search(int[] arr, int target) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) return true;

            // can't determine sorted half — shrink both ends
            if (arr[low] == arr[mid] && arr[mid] == arr[high]) {
                low++; high--;
                continue;
            }

            // left half is sorted
            if (arr[low] <= arr[mid]) {
                if (arr[low] <= target && target < arr[mid])
                    high = mid - 1;
                else
                    low = mid + 1;
            }
            // right half is sorted
            else {
                if (arr[mid] < target && target <= arr[high])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 6, 0, 0, 1, 2};
        System.out.println(search(arr, 0));  // Output: true
        System.out.println(search(arr, 3));  // Output: false
    }
}
