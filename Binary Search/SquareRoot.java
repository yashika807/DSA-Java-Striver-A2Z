import java.util.*;

public class SquareRoot {

    // ===================================================
    // Approach 1 - Brute Force
    // Linear search from 1 to N, find largest i where i*i <= N
    // Time: O(sqrt N) | Space: O(1)
    // ===================================================
    public static int sqrtBrute(int n) {
        int ans = 1;
        for (int i = 1; i * i <= n; i++) ans = i;
        return ans;
    }

    // ===================================================
    // Approach 2 - Optimal (Binary Search on Answer)
    // Search space: [1, N]
    // If mid*mid <= N → mid is a valid floor sqrt, try bigger (go right)
    // If mid*mid >  N → too big, go left
    // Time: O(log N) | Space: O(1)
    // ===================================================
    public static int sqrt(int n) {
        int low = 1, high = n, ans = 1;

        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (mid * mid <= n) {
                ans = (int) mid;  // valid, try for larger
                low = (int) mid + 1;
            } else {
                high = (int) mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(sqrt(25));  // Output: 5
        System.out.println(sqrt(36));  // Output: 6
        System.out.println(sqrt(28));  // Output: 5  (floor sqrt)
    }
}
