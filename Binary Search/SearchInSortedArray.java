import java.util.*;

public class SearchInSortedArray {

    // ===================================================
    // Approach 1 - Brute Force
    // Linear search through the array
    // Time: O(N) | Space: O(1)
    // ===================================================
    public static int searchBrute(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == target) return i;
        return -1;
    }

    // ===================================================
    // Approach 2 - Optimal (Binary Search)
    // Eliminate half the search space each iteration
    // If target < mid → search left half
    // If target > mid → search right half
    // Time: O(log N) | Space: O(1)
    // ===================================================
    public static int search(int[] arr, int target) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // avoids integer overflow

            if (arr[mid] == target)      return mid;
            else if (arr[mid] < target)  low = mid + 1;
            else                         high = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {-1, 0, 3, 5, 9, 12};
        System.out.println(search(arr, 9));   // Output: 4
        System.out.println(search(arr, 2));   // Output: -1
    }
}
