import java.util.*;

public class SearchIn2DMatrixII {

    // ===================================================
    // Matrix properties:
    // 1. Each row sorted left to right
    // 2. Each column sorted top to bottom
    // (Rows do NOT have the "first > last of prev" property)
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force
    // Linear scan every cell
    // Time: O(N * M) | Space: O(1)
    // ===================================================
    public static boolean searchMatrixBrute(int[][] mat, int target) {
        for (int[] row : mat)
            for (int x : row)
                if (x == target) return true;
        return false;
    }

    // ===================================================
    // Approach 2 - Better (Binary Search per row)
    // Binary search in each row independently
    // Time: O(N * log M) | Space: O(1)
    // ===================================================
    public static boolean searchMatrixBetter(int[][] mat, int target) {
        for (int[] row : mat) {
            int low = 0, high = row.length - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (row[mid] == target) return true;
                else if (row[mid] < target) low = mid + 1;
                else high = mid - 1;
            }
        }
        return false;
    }

    // ===================================================
    // Approach 3 - Optimal (Staircase Search)
    // Start at top-right corner: mat[0][M-1]
    // → if equal → found
    // → if target < current → move left  (eliminate current column)
    // → if target > current → move down  (eliminate current row)
    // Guaranteed to find in O(N + M) steps
    // Time: O(N + M) | Space: O(1)
    // ===================================================
    public static boolean searchMatrix(int[][] mat, int target) {
        int n = mat.length, m = mat[0].length;
        int row = 0, col = m - 1; // start from top-right

        while (row < n && col >= 0) {
            if (mat[row][col] == target)      return true;
            else if (mat[row][col] > target)  col--; // eliminate col
            else                              row++; // eliminate row
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] mat = {
            {1,  4,  7, 11},
            {2,  5,  8, 12},
            {3,  6,  9, 16},
            {10, 13, 14, 17}
        };
        System.out.println(searchMatrix(mat, 5));   // Output: true
        System.out.println(searchMatrix(mat, 20));  // Output: false
    }
}
