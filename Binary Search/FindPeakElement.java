import java.util.*;

public class FindPeakElement {

    // ===================================================
    // Peak element: arr[i] > arr[i-1] AND arr[i] > arr[i+1]
    // Assume arr[-1] = arr[n] = -infinity
    // Any peak index is valid (multiple peaks may exist)
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force
    // Linear scan for any peak
    // Time: O(N) | Space: O(1)
    // ===================================================
    public static int findPeakIndexBrute(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            boolean leftOk  = (i == 0)     || arr[i] > arr[i - 1];
            boolean rightOk = (i == n - 1) || arr[i] > arr[i + 1];
            if (leftOk && rightOk) return i;
        }
        return -1;
    }

    // ===================================================
    // Approach 2 - Optimal (Binary Search)
    // Key insight: move towards the side with a greater neighbour
    // → that side always has a peak (by the mountain guarantee)
    //
    // If arr[mid] > arr[mid+1] → we're on a descending slope → peak in left half (or at mid)
    // If arr[mid] < arr[mid+1] → we're on an ascending slope → peak in right half
    //
    // Time: O(log N) | Space: O(1)
    // ===================================================
    public static int findPeakIndex(int[] arr) {
        int n = arr.length;
        if (n == 1) return 0;
        if (arr[0] > arr[1]) return 0;
        if (arr[n - 1] > arr[n - 2]) return n - 1;

        int low = 1, high = n - 2;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1])
                return mid; // peak found

            if (arr[mid] > arr[mid - 1])
                low = mid + 1;  // ascending → peak in right
            else
                high = mid - 1; // descending → peak in left
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 1};
        System.out.println(findPeakIndex(arr)); // Output: 2

        int[] arr2 = {1, 2, 1, 3, 5, 6, 4};
        System.out.println(findPeakIndex(arr2)); // Output: 5 or 1 (any valid peak)
    }
}
