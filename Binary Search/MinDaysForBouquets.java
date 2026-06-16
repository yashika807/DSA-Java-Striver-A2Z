import java.util.*;

public class MinDaysForBouquets {

    // ===================================================
    // Problem: bloomDay[i] = day flower i blooms
    // Need M bouquets, each needing K adjacent bloomed flowers
    // Find minimum day to achieve this. Return -1 if impossible.
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force
    // Try every day from min(bloomDay) to max(bloomDay)
    // Return first day where M bouquets are possible
    // Time: O(max(bloomDay) * N) | Space: O(1)
    // ===================================================
    public static int minDaysBrute(int[] bloomDay, int m, int k) {
        int minDay = Arrays.stream(bloomDay).min().getAsInt();
        int maxDay = Arrays.stream(bloomDay).max().getAsInt();
        for (int day = minDay; day <= maxDay; day++) {
            if (canMake(bloomDay, m, k, day)) return day;
        }
        return -1;
    }

    // ===================================================
    // Approach 2 - Optimal (Binary Search on Answer)
    // Search space: [min(bloomDay), max(bloomDay)]
    // If on 'mid' day we can make M bouquets → try earlier (go left)
    // Else → need more time (go right)
    // Time: O(N * log(max(bloomDay))) | Space: O(1)
    // ===================================================
    private static boolean canMake(int[] bloomDay, int m, int k, int day) {
        int bouquets = 0, consecutive = 0;
        for (int bloom : bloomDay) {
            if (bloom <= day) {
                consecutive++;
                if (consecutive == k) { bouquets++; consecutive = 0; }
            } else {
                consecutive = 0; // reset streak
            }
        }
        return bouquets >= m;
    }

    public static int minDays(int[] bloomDay, int m, int k) {
        long total = (long) m * k;
        if (total > bloomDay.length) return -1; // impossible

        int low = Arrays.stream(bloomDay).min().getAsInt();
        int high = Arrays.stream(bloomDay).max().getAsInt();
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canMake(bloomDay, m, k, mid)) {
                ans = mid;
                high = mid - 1; // try earlier day
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] bloomDay = {1, 10, 3, 10, 2};
        System.out.println(minDays(bloomDay, 3, 1));  // Output: 3
        System.out.println(minDays(bloomDay, 3, 2));  // Output: -1
    }
}
