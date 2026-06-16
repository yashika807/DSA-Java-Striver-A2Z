import java.util.*;

public class AggressiveCows {

    // ===================================================
    // Problem: Place K cows in N stalls (given positions)
    // Maximize the minimum distance between any two cows
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force
    // Sort stalls. Try every possible min distance from 1 to max-min
    // Return the largest distance where K cows can be placed
    // Time: O(N log N + (max-min) * N) | Space: O(1)
    // ===================================================
    public static int aggressiveCowsBrute(int[] stalls, int k) {
        Arrays.sort(stalls);
        int ans = 1;
        int maxDist = stalls[stalls.length - 1] - stalls[0];
        for (int dist = 1; dist <= maxDist; dist++) {
            if (canPlace(stalls, k, dist)) ans = dist;
            else break;
        }
        return ans;
    }

    // ===================================================
    // Approach 2 - Optimal (Binary Search on Answer)
    // Search space: [1, max(stalls) - min(stalls)]
    // If minDist = mid allows placing K cows → valid, try larger (go right)
    // Else → too large, go left
    // Time: O(N log N + N * log(max-min)) | Space: O(1)
    // ===================================================
    private static boolean canPlace(int[] stalls, int k, int minDist) {
        int count = 1, lastPos = stalls[0];
        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - lastPos >= minDist) {
                count++;
                lastPos = stalls[i];
            }
        }
        return count >= k;
    }

    public static int aggressiveCows(int[] stalls, int k) {
        Arrays.sort(stalls);
        int low = 1, high = stalls[stalls.length - 1] - stalls[0];
        int ans = 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canPlace(stalls, k, mid)) {
                ans = mid;
                low = mid + 1;  // try larger min distance
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] stalls = {1, 2, 4, 8, 9};
        System.out.println(aggressiveCows(stalls, 3));  // Output: 3
    }
}
