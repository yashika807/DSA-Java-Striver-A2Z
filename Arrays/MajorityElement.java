import java.util.*;

public class MajorityElement {

    // ===================================================
    // Approach 1 - Brute Force
    // For each element, count its frequency
    // If frequency > N/2, return it
    // Time: O(N^2) | Space: O(1)
    // ===================================================
    public static int majorityElementBrute(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (arr[j] == arr[i]) count++;
            }
            if (count > n / 2) return arr[i];
        }
        return -1;
    }

    // ===================================================
    // Approach 2 - Better (HashMap)
    // Store frequency of each element in a map
    // Return the one with frequency > N/2
    // Time: O(N) | Space: O(N)
    // ===================================================
    public static int majorityElementBetter(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = arr.length;
        for (int x : arr) {
            map.put(x, map.getOrDefault(x, 0) + 1);
            if (map.get(x) > n / 2) return x;
        }
        return -1;
    }

    // ===================================================
    // Approach 3 - Optimal (Moore's Voting Algorithm)
    // Intuition: majority element votes cancel out all others
    // Phase 1: Find candidate
    // Phase 2: Verify candidate (if guaranteed majority, skip phase 2)
    // Time: O(N) | Space: O(1)
    // ===================================================
    public static int majorityElement(int[] arr) {
        int n = arr.length;

        // Phase 1 - find candidate
        int candidate = -1, count = 0;
        for (int x : arr) {
            if (count == 0) candidate = x;
            if (x == candidate) count++;
            else count--;
        }

        // Phase 2 - verify (needed if majority not guaranteed)
        count = 0;
        for (int x : arr) {
            if (x == candidate) count++;
        }
        return count > n / 2 ? candidate : -1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 2, 1, 1, 2, 2, 2};
        System.out.println(majorityElement(arr));
        // Output: 2
    }
}
