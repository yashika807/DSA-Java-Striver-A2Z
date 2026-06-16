import java.util.*;

public class KthElementOfTwoSortedArrays {

    // ===================================================
    // Approach 1 - Brute Force
    // Merge both arrays, return element at index k-1
    // Time: O(N + M) | Space: O(N + M)
    // ===================================================
    public static int kthElementBrute(int[] a, int[] b, int k) {
        int n = a.length, m = b.length;
        int[] merged = new int[n + m];
        int i = 0, j = 0, idx = 0;
        while (i < n && j < m) merged[idx++] = a[i] < b[j] ? a[i++] : b[j++];
        while (i < n) merged[idx++] = a[i++];
        while (j < m) merged[idx++] = b[j++];
        return merged[k - 1];
    }

    // ===================================================
    // Approach 2 - Better (Two pointers, stop at kth)
    // Traverse merged order, count until kth element
    // Time: O(K) | Space: O(1)
    // ===================================================
    public static int kthElementBetter(int[] a, int[] b, int k) {
        int i = 0, j = 0, count = 0, last = -1;
        while (i < a.length || j < b.length) {
            int val;
            if (j >= b.length || (i < a.length && a[i] <= b[j])) val = a[i++];
            else val = b[j++];
            count++;
            if (count == k) return val;
        }
        return -1;
    }

    // ===================================================
    // Approach 3 - Optimal (Binary Search)
    // Binary search on smaller array to find partition
    // such that left half has exactly k elements total
    //
    // l1 elements from a, l2 = k - l1 elements from b in the left half
    // Valid if: a[l1-1] <= b[l2] AND b[l2-1] <= a[l1]
    //
    // Time: O(log(min(N, M))) | Space: O(1)
    // ===================================================
    public static int kthElement(int[] a, int[] b, int k) {
        int n = a.length, m = b.length;
        if (n > m) return kthElement(b, a, k); // binary search on smaller

        int low = Math.max(0, k - m);  // l1 lower bound
        int high = Math.min(k, n);     // l1 upper bound

        while (low <= high) {
            int l1 = low + (high - low) / 2;
            int l2 = k - l1;

            int aLeft  = l1 == 0 ? Integer.MIN_VALUE : a[l1 - 1];
            int aRight = l1 == n ? Integer.MAX_VALUE : a[l1];
            int bLeft  = l2 == 0 ? Integer.MIN_VALUE : b[l2 - 1];
            int bRight = l2 == m ? Integer.MAX_VALUE : b[l2];

            if (aLeft <= bRight && bLeft <= aRight)
                return Math.max(aLeft, bLeft); // kth element is max of left halves

            else if (aLeft > bRight) high = l1 - 1;
            else low = l1 + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {2, 3, 6, 7, 9};
        int[] b = {1, 4, 8, 10};
        System.out.println(kthElement(a, b, 5));  // Output: 6
    }
}
