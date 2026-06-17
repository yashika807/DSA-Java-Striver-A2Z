import java.util.*;

public class LongestCommonPrefix {

    // ===================================================
    // Approach 1 - Brute Force (Character by Character)
    // Take first string as reference, compare each character
    // with the same position in all other strings
    // Time: O(N * M)  N = number of strings, M = min length | Space: O(1)
    // ===================================================
    public static String longestCommonPrefixBrute(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    // ===================================================
    // Approach 2 - Better (Sort + Compare First and Last)
    // After sorting, the lexicographically smallest and largest strings
    // are most different → their common prefix is the answer
    // Time: O(N log N + M) | Space: O(1)
    // ===================================================
    public static String longestCommonPrefixBetter(String[] strs) {
        Arrays.sort(strs);
        String first = strs[0], last = strs[strs.length - 1];
        int i = 0;
        while (i < first.length() && i < last.length() && first.charAt(i) == last.charAt(i))
            i++;
        return first.substring(0, i);
    }

    // ===================================================
    // Approach 3 - Optimal (Vertical Scanning)
    // Scan column by column (character by character across all strings)
    // Stop when mismatch found or any string ends
    // Time: O(N * M) | Space: O(1)
    // ===================================================
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        for (int col = 0; col < strs[0].length(); col++) {
            char c = strs[0].charAt(col);
            for (int row = 1; row < strs.length; row++) {
                if (col >= strs[row].length() || strs[row].charAt(col) != c)
                    return strs[0].substring(0, col);
            }
        }
        return strs[0];
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        // Output: "fl"

        System.out.println(longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
        // Output: ""
    }
}
