import java.util.*;

public class Sort012 {

    // ===================================================
    // Approach 1 - Brute Force
    // Just sort the array using built-in sort
    // Time: O(N log N) | Space: O(1)
    // ===================================================
    public static void sortBrute(int[] arr) {
        Arrays.sort(arr);
    }

    // ===================================================
    // Approach 2 - Better (Counting)
    // Count 0s, 1s, 2s → overwrite array
    // Time: O(2N) | Space: O(1)
    // ===================================================
    public static void sortBetter(int[] arr) {
        int count0 = 0, count1 = 0, count2 = 0;
        for (int x : arr) {
            if (x == 0)      count0++;
            else if (x == 1) count1++;
            else             count2++;
        }
        int i = 0;
        while (count0-- > 0) arr[i++] = 0;
        while (count1-- > 0) arr[i++] = 1;
        while (count2-- > 0) arr[i++] = 2;
    }

    // ===================================================
    // Approach 3 - Optimal (Dutch National Flag Algorithm)
    // Three pointers: low, mid, high
    // [0..low-1]  → all 0s
    // [low..mid-1] → all 1s
    // [mid..high] → unsorted
    // [high+1..n-1] → all 2s
    // Time: O(N) | Space: O(1)
    // ===================================================
    public static void sort012(int[] arr) {
        int low = 0, mid = 0, high = arr.length - 1;

        while (mid <= high) {
            if (arr[mid] == 0) {
                // swap with low, both pointers move right
                int temp = arr[low]; arr[low] = arr[mid]; arr[mid] = temp;
                low++; mid++;
            } else if (arr[mid] == 1) {
                // 1 is in correct region, just move mid
                mid++;
            } else {
                // swap with high, only high moves left (mid stays to recheck)
                int temp = arr[mid]; arr[mid] = arr[high]; arr[high] = temp;
                high--;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 0, 1, 2, 1, 0};
        sort012(arr);
        System.out.println(Arrays.toString(arr));
        // Output: [0, 0, 0, 1, 1, 1, 2, 2]
    }
}
