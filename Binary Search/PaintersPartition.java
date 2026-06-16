import java.util.*;

public class PaintersPartition {

    // ===================================================
    // Problem: N boards, K painters. Each painter paints contiguous boards.
    // Minimize the maximum boards (time) any painter has to paint.
    // (Identical pattern to Book Allocation / Split Array)
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force
    // Try every max limit from max(boards) to sum(boards)
    // Return first limit where K painters are sufficient
    // Time: O((sum - max) * N) | Space: O(1)
    // ===================================================
    public static int paintersBrute(int[] boards, int k) {
        int low = Arrays.stream(boards).max().getAsInt();
        int high = Arrays.stream(boards).sum();
        for (int limit = low; limit <= high; limit++) {
            if (countPainters(boards, limit) <= k) return limit;
        }
        return high;
    }

    // ===================================================
    // Approach 2 - Optimal (Binary Search on Answer)
    // Search space: [max(boards), sum(boards)]
    // Time: O(N * log(sum - max)) | Space: O(1)
    // ===================================================
    private static int countPainters(int[] boards, int maxWork) {
        int painters = 1, currentWork = 0;
        for (int b : boards) {
            if (currentWork + b > maxWork) {
                painters++;
                currentWork = 0;
            }
            currentWork += b;
        }
        return painters;
    }

    public static int painters(int[] boards, int k) {
        int low = Arrays.stream(boards).max().getAsInt();
        int high = Arrays.stream(boards).sum();
        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (countPainters(boards, mid) <= k) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] boards = {10, 20, 30, 40};
        System.out.println(painters(boards, 2));  // Output: 60
    }
}
