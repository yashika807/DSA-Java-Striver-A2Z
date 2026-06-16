import java.util.*;

public class SearchIn2DMatrix {

    // ===================================================
    // Matrix properties:
    // 1. Each row is sorted left to right
    // 2. First element of each row > last element of previous row
    // (Matrix is effectively one sorted 1D array)
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
    // Approach 2 - Better (Binary Search row by row)
    // Find the row where target could exist, then binary search in it
    // Time: O(N + log M) | Space: O(1)
    // ===================================================
    public static boolean searchMatrixBetter(int[][] mat, int target) {
        int n = mat.length, m = mat[0].length;
        // find the right row
        int row = -1;
        for (int i = 0; i < n; i++) {
            if (mat[i][0] <= target && target <= mat[i][m - 1]) { row = i; break; }
        }
        if (row == -1) return false;

        // binary search in that row
        int low = 0, high = m - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (mat[row][mid] == target) return true;
            else if (mat[row][mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return false;
    }

    // ===================================================
    // Approach 3 - Optimal (Treat as 1D sorted array)
    // Virtual index: row = mid / M, col = mid % M
    // Binary search on range [0, N*M - 1]
    // Time: O(log(N * M)) | Space: O(1)
    // ===================================================
    public static boolean searchMatrix(int[][] mat, int target) {
        int n = mat.length, m = mat[0].length;
        int low = 0, high = n * m - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int row = mid / m, col = mid % m;

            if (mat[row][col] == target)     return true;
            else if (mat[row][col] < target) low = mid + 1;
            else                             high = mid - 1;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] mat = {
            {1,  3,  5,  7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        System.out.println(searchMatrix(mat, 3));   // Output: true
        System.out.println(searchMatrix(mat, 13));  // Output: false
    }
}
