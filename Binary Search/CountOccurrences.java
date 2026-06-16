import java.util.*;

public class CountOccurrences {

    // ===================================================
    // Approach 1 - Brute Force
    // Linear scan and count
    // Time: O(N) | Space: O(1)
    // ===================================================
    public static int countBrute(int[] arr, int target) {
        int count = 0;
        for (int x : arr) if (x == target) count++;
        return count;
    }

    // ===================================================
    // Approach 2 - Optimal (First + Last Occurrence)
    // count = lastOccurrence - firstOccurrence + 1
    // Both found using binary search
    // Time: O(2 log N) = O(log N) | Space: O(1)
    // ===================================================
    public static int firstOccurrence(int[] arr, int target) {
        int low = 0, high = arr.length - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) { ans = mid; high = mid - 1; }
            else if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return ans;
    }

    public static int lastOccurrence(int[] arr, int target) {
        int low = 0, high = arr.length - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) { ans = mid; low = mid + 1; }
            else if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return ans;
    }

    public static int countOccurrences(int[] arr, int target) {
        int first = firstOccurrence(arr, target);
        if (first == -1) return 0; // target not in array
        int last = lastOccurrence(arr, target);
        return last - first + 1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 2, 2, 3, 4};
        System.out.println(countOccurrences(arr, 2));  // Output: 3
        System.out.println(countOccurrences(arr, 5));  // Output: 0
    }
}
