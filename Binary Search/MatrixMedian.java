import java.util.*;

public class MatrixMedian {

    // ===================================================
    // Problem: N x M matrix where each row is sorted.
    // Find the median of all N*M elements.
    // N*M is always odd (guaranteed unique median)
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force
    // Collect all elements, sort, return middle element
    // Time: O(N*M * log(N*M)) | Space: O(N*M)
    // ===================================================
    public static int matrixMedianBrute(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int[] all = new int[n * m];
        int idx = 0;
        for (int[] row : mat) for (int x : row) all[idx++] = x;
        Arrays.sort(all);
        return all[(n * m) / 2];
    }

    // ===================================================
    // Approach 2 - Optimal (Binary Search on Value)
    // Search space: [min element, max element]
    // For a given mid value, count elements <= mid using binary search on each row
    // Median is the smallest value where count >= (N*M+1)/2
    //
    // countLessOrEqual uses upperBound on each sorted row
    //
    // Time: O(32 * N * log M)  [32 = integer bit range] | Space: O(1)
    // ===================================================
    private static int upperBound(int[] row, int val) {
        int low = 0, high = row.length, ans = row.length;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (mid < row.length && row[mid] <= val) low = mid + 1;
            else { ans = mid; high = mid - 1; }
        }
        return ans;
    }

    private static int countLessOrEqual(int[][] mat, int val) {
        int count = 0;
        for (int[] row : mat) count += upperBound(row, val);
        return count;
    }

    public static int matrixMedian(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE;

        for (int[] row : mat) {
            low  = Math.min(low,  row[0]);       // min of first column
            high = Math.max(high, row[m - 1]);   // max of last column
        }

        int required = (n * m + 1) / 2; // median position (1-indexed)

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (countLessOrEqual(mat, mid) >= required)
                high = mid - 1; // mid could be median, try smaller
            else
                low = mid + 1;
        }
        return low;
    }

    public static void main(String[] args) {
        int[][] mat = {
            {1, 3, 5},
            {2, 6, 9},
            {3, 6, 9}
        };
        System.out.println(matrixMedian(mat)); // Output: 5
    }
}
