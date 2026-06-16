import java.util.*;

public class FindPeakElementII {

    // ===================================================
    // Peak in 2D matrix: mat[i][j] is a peak if it is
    // strictly greater than all its 4 neighbours (up, down, left, right)
    // Boundary elements have fewer neighbours to compare
    // Return any valid peak index
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force
    // Check every cell, verify all 4 neighbours
    // Time: O(N * M) | Space: O(1)
    // ===================================================
    public static int[] findPeakGridBrute(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                boolean isPeak = true;
                for (int d = 0; d < 4; d++) {
                    int ni = i + dr[d], nj = j + dc[d];
                    if (ni >= 0 && ni < n && nj >= 0 && nj < m
                            && mat[ni][nj] >= mat[i][j]) {
                        isPeak = false; break;
                    }
                }
                if (isPeak) return new int[]{i, j};
            }
        }
        return new int[]{-1, -1};
    }

    // ===================================================
    // Approach 2 - Optimal (Binary Search on columns)
    // Binary search on column index:
    //   For mid column, find the row with the maximum element in that column
    //   Check if it's a peak (compare with left and right neighbours)
    //   If left neighbour is larger → peak is in left half
    //   If right neighbour is larger → peak is in right half
    //   Else → found the peak
    //
    // Time: O(N * log M) | Space: O(1)
    // ===================================================
    public static int[] findPeakGrid(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int low = 0, high = m - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // find row index of max element in column mid
            int maxRow = 0;
            for (int i = 0; i < n; i++) {
                if (mat[i][mid] > mat[maxRow][mid]) maxRow = i;
            }

            int left  = mid - 1 >= 0 ? mat[maxRow][mid - 1] : -1;
            int right = mid + 1 < m  ? mat[maxRow][mid + 1] : -1;

            if (mat[maxRow][mid] > left && mat[maxRow][mid] > right)
                return new int[]{maxRow, mid}; // peak found

            else if (left > mat[maxRow][mid])
                high = mid - 1; // peak in left half

            else
                low = mid + 1;  // peak in right half
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[][] mat = {
            {1, 4},
            {3, 2}
        };
        System.out.println(Arrays.toString(findPeakGrid(mat)));
        // Output: [0, 1] or [1, 0] — any valid peak
    }
}
