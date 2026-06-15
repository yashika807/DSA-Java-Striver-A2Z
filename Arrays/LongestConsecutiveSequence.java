import java.util.*;

public class LongestConsecutiveSequence {

    // ===================================================
    // Approach 1 - Brute Force
    // For each element x, linearly search for x+1, x+2 ... 
    // Track the longest such chain
    // Time: O(N^3) | Space: O(1)
    // ===================================================
    public static int longestConsecutiveBrute(int[] arr) {
        int n = arr.length, longest = 1;

        for (int x : arr) {
            int current = x, count = 1;
            while (linearSearch(arr, current + 1)) {
                current++;
                count++;
            }
            longest = Math.max(longest, count);
        }
        return longest;
    }

    private static boolean linearSearch(int[] arr, int target) {
        for (int x : arr) if (x == target) return true;
        return false;
    }

    // ===================================================
    // Approach 2 - Better (Sorting)
    // Sort array, count consecutive streaks
    // Skip duplicates, reset count on gap
    // Time: O(N log N) | Space: O(1)
    // ===================================================
    public static int longestConsecutiveBetter(int[] arr) {
        Arrays.sort(arr);
        int longest = 1, count = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1] + 1) {
                count++;
                longest = Math.max(longest, count);
            } else if (arr[i] != arr[i - 1]) {
                count = 1; // gap found, reset
            }
            // if arr[i] == arr[i-1], skip duplicate
        }
        return longest;
    }

    // ===================================================
    // Approach 3 - Optimal (HashSet)
    // Add all elements to a set
    // Only start counting from sequence start (x-1 not in set)
    // Avoids redundant counting from middle of sequences
    // Time: O(N) | Space: O(N)
    // ===================================================
    public static int longestConsecutive(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int x : arr) set.add(x);

        int longest = 1;

        for (int x : set) {
            // only start a new sequence if x is the beginning
            if (!set.contains(x - 1)) {
                int current = x, count = 1;
                while (set.contains(current + 1)) {
                    current++;
                    count++;
                }
                longest = Math.max(longest, count);
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        int[] arr = {100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive(arr));
        // Output: 4  →  sequence [1, 2, 3, 4]
    }
}
