import java.util.*;

public class MedianOfTwoSortedArrays {

    // ===================================================
    // Approach 1 - Brute Force
    // Merge both arrays, return middle element(s)
    // Time: O(N + M) | Space: O(N + M)
    // ===================================================
    public static double findMedianSortedArraysBrute(int[] a, int[] b) {
        int n = a.length, m = b.length;
        int[] merged = new int[n + m];
        int i = 0, j = 0, k = 0;

        while (i < n && j < m)
            merged[k++] = a[i] < b[j] ? a[i++] : b[j++];
        while (i < n) merged[k++] = a[i++];
        while (j < m) merged[k++] = b[j++];

        int total = n + m;
        if (total % 2 == 1) return merged[total / 2];
        return (merged[total / 2 - 1] + merged[total / 2]) / 2.0;
    }

    // ===================================================
    // Approach 2 - Better (Without extra space)
    // Use two pointers, traverse only up to median index
    // Time: O((N + M) / 2) | Space: O(1)
    // ===================================================
    public static double findMedianBetter(int[] a, int[] b) {
        int n = a.length, m = b.length, total = n + m;
        int mid1 = total / 2, mid2 = mid1 - 1;
        int i = 0, j = 0, count = 0;
        int el1 = -1, el2 = -1;

        while (i < n || j < m) {
            int val;
            if (j >= m || (i < n && a[i] <= b[j])) val = a[i++];
            else val = b[j++];

            if (count == mid2) el2 = val;
            if (count == mid1) { el1 = val; break; }
            count++;
        }
        if (total % 2 == 1) return el1;
        return (el1 + el2) / 2.0;
    }

    // ===================================================
    // Approach 3 - Optimal (Binary Search)
    // Binary search on the smaller array to find the correct partition
    // such that left half of merged array contains (N+M)/2 elements
    //
    // For partition at l1 in a[], l2 = (N+M+1)/2 - l1 in b[]
    // Valid if: a[l1-1] <= b[l2] AND b[l2-1] <= a[l1]
    //
    // Time: O(log(min(N, M))) | Space: O(1)
    // ===================================================
    public static double findMedianSortedArrays(int[] a, int[] b) {
        int n = a.length, m = b.length;
        if (n > m) return findMedianSortedArrays(b, a); // ensure a is smaller

        int low = 0, high = n;
        int left = (n + m + 1) / 2;

        while (low <= high) {
            int l1 = low + (high - low) / 2;
            int l2 = left - l1;

            int aLeft  = l1 == 0 ? Integer.MIN_VALUE : a[l1 - 1];
            int aRight = l1 == n ? Integer.MAX_VALUE : a[l1];
            int bLeft  = l2 == 0 ? Integer.MIN_VALUE : b[l2 - 1];
            int bRight = l2 == m ? Integer.MAX_VALUE : b[l2];

            if (aLeft <= bRight && bLeft <= aRight) {
                // valid partition found
                if ((n + m) % 2 == 1) return Math.max(aLeft, bLeft);
                return (Math.max(aLeft, bLeft) + Math.min(aRight, bRight)) / 2.0;
            } else if (aLeft > bRight) {
                high = l1 - 1; // move left in a
            } else {
                low = l1 + 1;  // move right in a
            }
        }
        return 0.0;
    }

    public static void main(String[] args) {
        int[] a = {1, 3}, b = {2};
        System.out.println(findMedianSortedArrays(a, b)); // Output: 2.0

        int[] a2 = {1, 2}, b2 = {3, 4};
        System.out.println(findMedianSortedArrays(a2, b2)); // Output: 2.5
    }
}
