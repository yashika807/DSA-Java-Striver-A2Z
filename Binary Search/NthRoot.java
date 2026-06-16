import java.util.*;

public class NthRoot {

    // ===================================================
    // Approach 1 - Brute Force
    // Linear search from 1 to M, find i where i^N == M
    // Time: O(M * log N) | Space: O(1)
    // ===================================================
    public static int nthRootBrute(int n, int m) {
        for (int i = 1; i <= m; i++) {
            long power = (long) Math.pow(i, n);
            if (power == m) return i;
            if (power > m)  break;
        }
        return -1;
    }

    // ===================================================
    // Approach 2 - Optimal (Binary Search on Answer)
    // Search space: [1, M]
    // Compute mid^N:
    //   == M → found answer
    //   <  M → go right (need larger base)
    //   >  M → go left  (base too large)
    //
    // Use a helper to compute power carefully (avoid overflow)
    // Return 1 if power == m, 2 if power > m (to stop early), 0 if < m
    //
    // Time: O(log M * log N) | Space: O(1)
    // ===================================================
    private static int power(long mid, int n, int m) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= mid;
            if (result > m) return 2; // overflow guard, too big
        }
        return result == m ? 1 : 0;
    }

    public static int nthRoot(int n, int m) {
        int low = 1, high = m;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int check = power(mid, n, m);

            if (check == 1) return mid;      // exact match
            else if (check == 0) low = mid + 1;  // too small
            else high = mid - 1;                 // too large
        }
        return -1; // no integer Nth root
    }

    public static void main(String[] args) {
        System.out.println(nthRoot(3, 27));   // Output: 3  (3^3 = 27)
        System.out.println(nthRoot(2, 16));   // Output: 4  (4^2 = 16)
        System.out.println(nthRoot(3, 26));   // Output: -1 (no integer root)
    }
}
