import java.util.*;

public class SetMatrixZeroes {

    // ===================================================
    // Approach 1 - Brute Force
    // Mark cells to be zeroed with a placeholder (-1)
    // Then replace -1 with 0 in a second pass
    // (Assumes -1 is not a valid value in matrix)
    // Time: O((N*M) * (N+M)) | Space: O(1)
    // ===================================================
    public static void setZeroesBrute(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    // mark entire row
                    for (int k = 0; k < m; k++)
                        if (matrix[i][k] != 0) matrix[i][k] = -1;
                    // mark entire col
                    for (int k = 0; k < n; k++)
                        if (matrix[k][j] != 0) matrix[k][j] = -1;
                }
            }
        }
        // replace -1 with 0
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (matrix[i][j] == -1) matrix[i][j] = 0;
    }

    // ===================================================
    // Approach 2 - Better (Row and Col boolean arrays)
    // Use two boolean arrays to track which rows/cols have a 0
    // Second pass: zero out those rows and cols
    // Time: O(2 * N*M) | Space: O(N + M)
    // ===================================================
    public static void setZeroesBetter(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        boolean[] rows = new boolean[n];
        boolean[] cols = new boolean[m];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (matrix[i][j] == 0) { rows[i] = true; cols[j] = true; }

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (rows[i] || cols[j]) matrix[i][j] = 0;
    }

    // ===================================================
    // Approach 3 - Optimal (Use first row and col as markers)
    // Use matrix[0][j] to mark zeroed cols
    // Use matrix[i][0] to mark zeroed rows
    // Extra variable col0 to handle conflict at matrix[0][0]
    // Time: O(2 * N*M) | Space: O(1)
    // ===================================================
    public static void setZeroes(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int col0 = 1; // tracks if first col should be zeroed

        // Step 1: mark using first row and first col
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0; // mark row
                    if (j == 0) col0 = 0;
                    else matrix[0][j] = 0; // mark col
                }
            }
        }

        // Step 2: zero inner cells (skip first row/col for now)
        for (int i = 1; i < n; i++)
            for (int j = 1; j < m; j++)
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;

        // Step 3: zero first row if needed
        if (matrix[0][0] == 0)
            for (int j = 0; j < m; j++) matrix[0][j] = 0;

        // Step 4: zero first col if needed
        if (col0 == 0)
            for (int i = 0; i < n; i++) matrix[i][0] = 0;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };
        setZeroes(matrix);
        for (int[] row : matrix) System.out.println(Arrays.toString(row));
        // Output:
        // [1, 0, 1]
        // [0, 0, 0]
        // [1, 0, 1]
    }
}
