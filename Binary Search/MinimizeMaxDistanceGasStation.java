import java.util.*;

public class MinimizeMaxDistanceGasStation {

    // ===================================================
    // Problem: Given N gas stations on a highway, add K more.
    // Minimize the maximum distance between adjacent stations.
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force
    // Use a max-heap (priority queue) to always place next station
    // in the largest gap, repeat K times
    // Time: O(N + K log N) | Space: O(N)
    // ===================================================
    public static double minimizeMaxDistBrute(int[] stations, int k) {
        int n = stations.length;
        int[] howMany = new int[n - 1]; // how many added in gap i

        for (int i = 0; i < k; i++) {
            // find current max gap
            double maxDist = -1;
            int maxIdx = -1;
            for (int j = 0; j < n - 1; j++) {
                double gap = (double)(stations[j + 1] - stations[j]) / (howMany[j] + 1);
                if (gap > maxDist) { maxDist = gap; maxIdx = j; }
            }
            howMany[maxIdx]++;
        }

        double ans = -1;
        for (int j = 0; j < n - 1; j++) {
            double gap = (double)(stations[j + 1] - stations[j]) / (howMany[j] + 1);
            ans = Math.max(ans, gap);
        }
        return ans;
    }

    // ===================================================
    // Approach 2 - Optimal (Binary Search on Answer with precision)
    // Search space: [0, max gap between adjacent stations]
    // For a given max distance 'mid', count extra stations needed
    //   → extra in gap = ceil(gapLength / mid) - 1
    // If total extra <= K → valid, try smaller (go left)
    // Else → go right
    // Use precision 1e-6 as termination
    // Time: O(N * log((max_gap) / 1e-6)) | Space: O(1)
    // ===================================================
    private static int countStationsNeeded(int[] stations, double maxDist) {
        int count = 0;
        for (int i = 0; i < stations.length - 1; i++) {
            int gap = stations[i + 1] - stations[i];
            count += (int)(gap / maxDist); // extra stations in this gap
        }
        return count;
    }

    public static double minimizeMaxDist(int[] stations, int k) {
        double low = 0, high = 0;
        for (int i = 0; i < stations.length - 1; i++)
            high = Math.max(high, stations[i + 1] - stations[i]);

        double ans = high;
        while (high - low > 1e-6) {
            double mid = (low + high) / 2.0;
            if (countStationsNeeded(stations, mid) <= k) {
                ans = mid;
                high = mid; // try smaller max distance
            } else {
                low = mid;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] stations = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.printf("%.5f%n", minimizeMaxDist(stations, 9));
        // Output: 0.50000
    }
}
