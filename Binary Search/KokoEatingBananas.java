import java.util.*;

public class KokoEatingBananas {

    // ===================================================
    // Approach 1 - Brute Force
    // Try every speed from 1 to max(piles)
    // Return first speed where hours <= h
    // Time: O(max(piles) * N) | Space: O(1)
    // ===================================================
    public static int minEatingSpeedBrute(int[] piles, int h) {
        int maxPile = Arrays.stream(piles).max().getAsInt();
        for (int speed = 1; speed <= maxPile; speed++) {
            if (canFinish(piles, speed, h)) return speed;
        }
        return maxPile;
    }

    // ===================================================
    // Approach 2 - Optimal (Binary Search on Answer)
    // Search space: [1, max(piles)]
    // If speed = mid can finish in <= h hours → valid, try slower (go left)
    // Else → too slow, go right
    // Time: O(N * log(max(piles))) | Space: O(1)
    // ===================================================
    private static boolean canFinish(int[] piles, int speed, int h) {
        long totalHours = 0;
        for (int pile : piles)
            totalHours += (pile + speed - 1) / speed; // ceil division
        return totalHours <= h;
    }

    public static int minEatingSpeed(int[] piles, int h) {
        int low = 1, high = Arrays.stream(piles).max().getAsInt();
        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canFinish(piles, mid, h)) {
                ans = mid;      // valid speed, try to minimize
                high = mid - 1;
            } else {
                low = mid + 1;  // too slow
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] piles = {3, 6, 7, 11};
        System.out.println(minEatingSpeed(piles, 8));  // Output: 4
    }
}
