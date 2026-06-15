import java.util.*;

public class LeadersInArray {

    // ===================================================
    // Approach 1 - Brute Force
    // For each element, check if all elements to its right are smaller
    // If yes, it's a leader
    // Time: O(N^2) | Space: O(1)
    // ===================================================
    public static List<Integer> leadersBrute(int[] arr) {
        int n = arr.length;
        List<Integer> leaders = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            boolean isLeader = true;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] >= arr[i]) {
                    isLeader = false;
                    break;
                }
            }
            if (isLeader) leaders.add(arr[i]);
        }
        return leaders;
    }

    // ===================================================
    // Approach 2 - Optimal (Traverse from Right)
    // Rightmost element is always a leader
    // Traverse from right to left, track max from right
    // If current element >= maxFromRight, it's a leader
    // Time: O(N) | Space: O(1)  (output list not counted)
    // ===================================================
    public static List<Integer> leaders(int[] arr) {
        int n = arr.length;
        List<Integer> leaders = new ArrayList<>();
        int maxFromRight = arr[n - 1];
        leaders.add(maxFromRight); // rightmost is always a leader

        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] >= maxFromRight) {
                maxFromRight = arr[i];
                leaders.add(arr[i]);
            }
        }

        Collections.reverse(leaders); // to maintain original order
        return leaders;
    }

    public static void main(String[] args) {
        int[] arr = {10, 22, 12, 3, 0, 6};
        System.out.println(leaders(arr));
        // Output: [22, 12, 6]
    }
}
