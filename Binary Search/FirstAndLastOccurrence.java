import java.util.*;

public class FirstAndLastOccurrence {

    // ===================================================
    // Approach 1 - Brute Force
    // Linear scan for first and last occurrence
    // Time: O(N) | Space: O(1)
    // ===================================================
    public static int[] searchRangeBrute(int[] arr, int target) {
        int first = -1, last = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                if (first == -1) first = i;
                last = i;
            }
        }
        return new int[]{first, last};
    }

    // ===================================================
    // Approach 2 - Optimal (Two Binary Searches)
    // First occurrence: when arr[mid] == target, record and go LEFT
    // Last occurrence : when arr[mid] == target, record and go RIGHT
    // Time: O(2 log N) = O(log N) | Space: O(1)
    // ===================================================
    public static int firstOccurrence(int[] arr, int target) {
        int low = 0, high = arr.length - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                ans = mid;
                high = mid - 1; // go left for earlier occurrence
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    public static int lastOccurrence(int[] arr, int target) {
        int low = 0, high = arr.length - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                ans = mid;
                low = mid + 1;  // go right for later occurrence
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    public static int[] searchRange(int[] arr, int target) {
        return new int[]{firstOccurrence(arr, target), lastOccurrence(arr, target)};
    }

    public static void main(String[] args) {
        int[] arr = {5, 7, 7, 8, 8, 10};
        System.out.println(Arrays.toString(searchRange(arr, 8)));  // Output: [3, 4]
        System.out.println(Arrays.toString(searchRange(arr, 6)));  // Output: [-1, -1]
    }
}
