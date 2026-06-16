import java.util.*;

public class FindRotationCount {

    // ===================================================
    // Problem: Array was sorted then rotated K times.
    // Find K (= index of minimum element)
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force
    // Find index of minimum element linearly
    // Time: O(N) | Space: O(1)
    // ===================================================
    public static int findRotationBrute(int[] arr) {
        int minIdx = 0;
        for (int i = 1; i < arr.length; i++)
            if (arr[i] < arr[minIdx]) minIdx = i;
        return minIdx;
    }

    // ===================================================
    // Approach 2 - Optimal (Binary Search)
    // Index of minimum element = number of rotations
    // Same logic as FindMinimum but track the index
    //
    // If left half sorted → arr[low] is candidate min, search right
    // If right half sorted → arr[mid] is candidate min, search left
    //
    // Time: O(log N) | Space: O(1)
    // ===================================================
    public static int findRotationCount(int[] arr) {
        int low = 0, high = arr.length - 1;
        int ansIdx = 0;
        int ansVal = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // entire range is already sorted
            if (arr[low] <= arr[high]) {
                if (arr[low] < ansVal) { ansVal = arr[low]; ansIdx = low; }
                break;
            }

            // left half sorted → arr[low] is min candidate
            if (arr[low] <= arr[mid]) {
                if (arr[low] < ansVal) { ansVal = arr[low]; ansIdx = low; }
                low = mid + 1;
            }
            // right half sorted → arr[mid] is min candidate
            else {
                if (arr[mid] < ansVal) { ansVal = arr[mid]; ansIdx = mid; }
                high = mid - 1;
            }
        }
        return ansIdx; // index of minimum = rotation count
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(findRotationCount(arr)); // Output: 4  (rotated 4 times)

        int[] arr2 = {3, 4, 5, 1, 2};
        System.out.println(findRotationCount(arr2)); // Output: 3
    }
}
