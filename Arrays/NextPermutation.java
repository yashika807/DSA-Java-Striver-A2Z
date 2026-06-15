import java.util.*;

public class NextPermutation {

    // ===================================================
    // Approach 1 - Brute Force
    // Generate all permutations in sorted order
    // Find current permutation, return the next one
    // Time: O(N! * N) | Space: O(N!)
    // (Not implemented — impractical, just the idea)
    // ===================================================

    // ===================================================
    // Approach 2 - Optimal (3 Steps)
    //
    // Step 1: Find the "break point" — rightmost index i
    //         where arr[i] < arr[i+1] (ascending from right breaks)
    //
    // Step 2: From the right, find the smallest element
    //         just greater than arr[i], swap it with arr[i]
    //
    // Step 3: Reverse the suffix after index i
    //         (suffix is in descending order — reversing makes it smallest)
    //
    // Edge case: if no break point found → entire array is descending
    //            → just reverse the whole array (wrap to first permutation)
    //
    // Time: O(N) | Space: O(1)
    // ===================================================
    public static void nextPermutation(int[] arr) {
        int n = arr.length;
        int breakPoint = -1;

        // Step 1: find break point
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] < arr[i + 1]) {
                breakPoint = i;
                break;
            }
        }

        // Edge case: last permutation → reverse all
        if (breakPoint == -1) {
            reverse(arr, 0, n - 1);
            return;
        }

        // Step 2: find smallest element > arr[breakPoint] from right, swap
        for (int i = n - 1; i > breakPoint; i--) {
            if (arr[i] > arr[breakPoint]) {
                int temp = arr[i]; arr[i] = arr[breakPoint]; arr[breakPoint] = temp;
                break;
            }
        }

        // Step 3: reverse the suffix
        reverse(arr, breakPoint + 1, n - 1);
    }

    private static void reverse(int[] arr, int l, int r) {
        while (l < r) {
            int temp = arr[l]; arr[l] = arr[r]; arr[r] = temp;
            l++; r--;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2};
        nextPermutation(arr);
        System.out.println(Arrays.toString(arr));
        // Output: [2, 1, 3]

        int[] arr2 = {3, 2, 1};
        nextPermutation(arr2);
        System.out.println(Arrays.toString(arr2));
        // Output: [1, 2, 3]  →  wraps to first permutation
    }
}
