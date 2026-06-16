import java.util.*;

public class ShipPackagesWithinDDays {

    // ===================================================
    // Approach 1 - Brute Force
    // Try every capacity from max(weights) to sum(weights)
    // Return first valid capacity
    // Time: O((sum - max) * N) | Space: O(1)
    // ===================================================
    public static int shipWithinDaysBrute(int[] weights, int days) {
        int low = Arrays.stream(weights).max().getAsInt();
        int high = Arrays.stream(weights).sum();
        for (int cap = low; cap <= high; cap++) {
            if (canShip(weights, cap, days)) return cap;
        }
        return high;
    }

    // ===================================================
    // Approach 2 - Optimal (Binary Search on Answer)
    // Search space: [max(weights), sum(weights)]
    //   min cap = max single weight (must carry heaviest package)
    //   max cap = total sum (carry all in 1 day)
    //
    // If capacity = mid can ship in <= days → try smaller (go left)
    // Else → go right
    // Time: O(N * log(sum - max)) | Space: O(1)
    // ===================================================
    private static boolean canShip(int[] weights, int capacity, int days) {
        int daysNeeded = 1, currentLoad = 0;
        for (int w : weights) {
            if (currentLoad + w > capacity) {
                daysNeeded++;        // start new day
                currentLoad = 0;
            }
            currentLoad += w;
        }
        return daysNeeded <= days;
    }

    public static int shipWithinDays(int[] weights, int days) {
        int low = Arrays.stream(weights).max().getAsInt();
        int high = Arrays.stream(weights).sum();
        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canShip(weights, mid, days)) {
                ans = mid;
                high = mid - 1; // try smaller capacity
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(shipWithinDays(weights, 5));  // Output: 15
    }
}
