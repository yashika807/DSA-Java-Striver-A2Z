import java.util.*;

public class RotateMatrix90 {

    // ===================================================
    // Approach 1 - Brute Force
    // Use an extra N x N matrix
    // For 90° clockwise: result[j][n-1-i] = matrix[i][j]
    // Time: O(N^2) | Space: O(N^2)
    // ===================================================
    public static int[][] rotateBrute(int[][] matrix) {
        int n = matrix.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                result[j][n - 1 - i] = matrix[i][j];
        return result;
    }

    // ===================================================
    // Approach 2 - Optimal (Transpose + Reverse each Row)
    // Step 1: Transpose the matrix (swap matrix[i][j] with matrix[j][i])
    // Step 2: Reverse each row
    // This achieves 90° clockwise rotation in-place
    // Time: O(N^2) | Space: O(1)
    // ===================================================
    public static void rotate(int[][] matrix) {
        int n = matrix.length;

        // Step 1: Transpose
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++; right--;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        rotate(matrix);
        for (int[] row : matrix) System.out.println(Arrays.toString(row));
        // Output:
        // [7, 4, 1]
        // [8, 5, 2]
        // [9, 6, 3]
    }
}
