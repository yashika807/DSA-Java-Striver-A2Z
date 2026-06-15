import java.util.*;

public class TwoSum {

    // ===================================================
    // Approach 1 - Brute Force
    // Check every pair (i, j), see if arr[i] + arr[j] == target
    // Time: O(N^2) | Space: O(1)
    // ===================================================
    public static int[] twoSumBrute(int[] arr, int target) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] + arr[j] == target)
                    return new int[]{i, j};
            }
        }
        return new int[]{-1, -1};
    }

    // ===================================================
    // Approach 2 - Better (HashMap)
    // For each element x, check if (target - x) exists in map
    // Store value -> index as we traverse
    // Time: O(N) | Space: O(N)
    // ===================================================
    public static int[] twoSumBetter(int[] arr, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            if (map.containsKey(complement))
                return new int[]{map.get(complement), i};
            map.put(arr[i], i);
        }
        return new int[]{-1, -1};
    }

    // ===================================================
    // Approach 3 - Optimal (Two Pointers)
    // Sort array, shrink window from both ends
    // NOTE: Returns YES/NO only (indices lost after sort)
    // Time: O(N log N) | Space: O(1)
    // ===================================================
    public static String twoSumOptimal(int[] arr, int target) {
        Arrays.sort(arr);
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target)      return "YES";
            else if (sum < target)  left++;
            else                    right--;
        }
        return "NO";
    }

    public static void main(String[] args) {
        int[] arr = {2, 6, 5, 8, 11};
        int target = 14;

        System.out.println(Arrays.toString(twoSumBetter(arr, target)));
        // Output: [1, 3]  →  arr[1]+arr[3] = 6+8 = 14

        System.out.println(twoSumOptimal(new int[]{2, 6, 5, 8, 11}, target));
        // Output: YES
    }
}
