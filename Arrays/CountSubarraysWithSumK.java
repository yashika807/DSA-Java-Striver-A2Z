import java.util.*;

public class CountSubarraysWithSumK {

    // ===================================================
    // Approach 1 - Brute Force
    // Generate all subarrays, compute sum, count those equal to K
    // Time: O(N^2) | Space: O(1)
    // ===================================================
    public static int subarraySumBrute(int[] arr, int k) {
        int n = arr.length, count = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[j];
                if (sum == k) count++;
            }
        }
        return count;
    }

    // ===================================================
    // Approach 2 - Optimal (Prefix Sum + HashMap)
    // Key idea: if prefixSum[j] - prefixSum[i] = K
    //           then subarray (i+1 to j) has sum K
    //
    // Store frequency of each prefix sum in a map
    // For each index, check how many times (sum - K) appeared before
    // That many subarrays ending here have sum = K
    //
    // Time: O(N) | Space: O(N)
    // ===================================================
    public static int subarraySum(int[] arr, int k) {
        HashMap<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1); // empty prefix (sum = 0 seen once)
        int sum = 0, count = 0;

        for (int x : arr) {
            sum += x;
            // how many times (sum - k) appeared → that many subarrays end here with sum k
            count += prefixCount.getOrDefault(sum - k, 0);
            prefixCount.put(sum, prefixCount.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, -3, 1, 1, 1, 4, 2, -3};
        int k = 3;
        System.out.println(subarraySum(arr, k));
        // Output: 8
    }
}
