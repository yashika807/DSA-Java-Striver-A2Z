import java.util.*;

public class SpiralMatrix {

    // ===================================================
    // Approach 1 - Brute Force
    // Use a visited boolean matrix
    // Simulate direction changes when hitting boundary or visited cell
    // Time: O(N*M) | Space: O(N*M)
    // ===================================================
    public static List<Integer> spiralOrderBrute(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        boolean[][] visited = new boolean[n][m];
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int r = 0, c = 0, dir = 0;
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n * m; i++) {
            result.add(matrix[r][c]);
            visited[r][c] = true;
            int nr = r + dr[dir], nc = c + dc[dir];
            if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc]) {
                r = nr; c = nc;
            } else {
                dir = (dir + 1) % 4;
                r += dr[dir]; c += dc[dir];
            }
        }
        return result;
    }

    // ===================================================
    // Approach 2 - Optimal (Layer by Layer using 4 boundaries)
    // Maintain top, bottom, left, right boundaries
    // Traverse: left→right, top→bottom, right→left, bottom→top
    // Shrink boundaries after each direction
    // Time: O(N*M) | Space: O(1)  (output not counted)
    // ===================================================
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            // Left to Right (top row)
            for (int i = left; i <= right; i++) result.add(matrix[top][i]);
            top++;

            // Top to Bottom (right col)
            for (int i = top; i <= bottom; i++) result.add(matrix[i][right]);
            right--;

            // Right to Left (bottom row)
            if (top <= bottom) {
                for (int i = right; i >= left; i--) result.add(matrix[bottom][i]);
                bottom--;
            }

            // Bottom to Top (left col)
            if (left <= right) {
                for (int i = bottom; i >= top; i--) result.add(matrix[i][left]);
                left++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1,  2,  3,  4},
            {5,  6,  7,  8},
            {9, 10, 11, 12}
        };
        System.out.println(spiralOrder(matrix));
        // Output: [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
    }
}
