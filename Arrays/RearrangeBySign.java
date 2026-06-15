import java.util.*;

public class RearrangeBySign {

    // ===================================================
    // Approach 1 - Brute Force
    // Separate positives and negatives into two lists
    // Merge alternately: pos[0], neg[0], pos[1], neg[1] ...
    // (Assumes equal count of positives and negatives)
    // Time: O(N) | Space: O(N)
    // ===================================================
    public static int[] rearrangeBrute(int[] arr) {
        List<Integer> pos = new ArrayList<>();
        List<Integer> neg = new ArrayList<>();

        for (int x : arr) {
            if (x >= 0) pos.add(x);
            else neg.add(x);
        }

        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length / 2; i++) {
            result[2 * i]     = pos.get(i);
            result[2 * i + 1] = neg.get(i);
        }
        return result;
    }

    // ===================================================
    // Approach 2 - Optimal (Two Pointer on result array)
    // posIdx starts at 0 (even positions for positives)
    // negIdx starts at 1 (odd positions for negatives)
    // Place directly without separating first
    // Time: O(N) | Space: O(N)
    // ===================================================
    public static int[] rearrange(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        int posIdx = 0, negIdx = 1;

        for (int x : arr) {
            if (x >= 0) {
                result[posIdx] = x;
                posIdx += 2;
            } else {
                result[negIdx] = x;
                negIdx += 2;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, -2, -5, 2, -4};
        System.out.println(Arrays.toString(rearrange(arr)));
        // Output: [3, -2, 1, -5, 2, -4]
    }
}
