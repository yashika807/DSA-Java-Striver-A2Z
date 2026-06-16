import java.util.*;

public class FloorAndCeil {

    // ===================================================
    // Floor: Largest element in array <= target
    // Ceil : Smallest element in array >= target
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force
    // Linear scan to find floor and ceil
    // Time: O(N) | Space: O(1)
    // ===================================================
    public static int[] floorCeilBrute(int[] arr, int target) {
        int floor = -1, ceil = -1;
        for (int x : arr) {
            if (x <= target) floor = x;       // keep updating — last valid is largest
        }
        for (int x : arr) {
            if (x >= target) { ceil = x; break; } // first valid is smallest
        }
        return new int[]{floor, ceil};
    }

    // ===================================================
    // Approach 2 - Optimal (Binary Search for both)
    // Floor → largest arr[mid] <= target → go right for bigger candidates
    // Ceil  → smallest arr[mid] >= target → go left for smaller candidates
    // Time: O(log N) | Space: O(1)
    // ===================================================
    public static int floor(int[] arr, int target) {
        int low = 0, high = arr.length - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] <= target) {
                ans = arr[mid]; // valid floor, try for a bigger one
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    public static int ceil(int[] arr, int target) {
        int low = 0, high = arr.length - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= target) {
                ans = arr[mid]; // valid ceil, try for a smaller one
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static int[] floorAndCeil(int[] arr, int target) {
        return new int[]{floor(arr, target), ceil(arr, target)};
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 8, 10, 11, 12, 19};
        System.out.println(Arrays.toString(floorAndCeil(arr, 5)));
        // Output: [2, 8]  →  floor=2, ceil=8

        System.out.println(Arrays.toString(floorAndCeil(arr, 8)));
        // Output: [8, 8]  →  exact match
    }
}
