import java.util.*;

public class RowWithMaxOnes {

    // ===================================================
    // Each row of the matrix is sorted (0s then 1s)
    // Find the row with the maximum number of 1s
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force
    // Count 1s in each row linearly
    // Time: O(N * M) | Space: O(1)
    // ===================================================
    public static int rowWithMaxOnesBrute(int[][] mat) {
        int maxOnes = -1, ansRow = -1;
        for (int i = 0; i < mat.length; i++) {
            int count = 0;
            for (int x : mat[i]) if (x == 1) count++;
            if (count > maxOnes) { maxOnes = count; ansRow = i; }
        }
        return ansRow;
    }

    // ===================================================
    // Approach 2 - Optimal (Binary Search per row)
    // For each row, binary search for first occurrence of 1
    // Count = M - firstOneIndex
    // Time: O(N * log M) | Space: O(1)
    // ===================================================
    private static int firstOne(int[] row) {
        int low = 0, high = row.length - 1, ans = row.length;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (row[mid] == 1) { ans = mid; high = mid - 1; }
            else low = mid + 1;
        }
        return ans;
    }

    public static int rowWithMaxOnes(int[][] mat) {
        int maxOnes = -1, ansRow = -1;
        int m = mat[0].length;

        for (int i = 0; i < mat.length; i++) {
            int firstIdx = firstOne(mat[i]);
            int count = m - firstIdx;
            if (count > maxOnes) { maxOnes = count; ansRow = i; }
        }
        return ansRow;
    }

    public static void main(String[] args) {
        int[][] mat = {
            {0, 0, 1, 1},
            {0, 0, 0, 0},
            {0, 1, 1, 1},
            {0, 0, 0, 0}
        };
        System.out.println(rowWithMaxOnes(mat)); // Output: 2
    }
}
