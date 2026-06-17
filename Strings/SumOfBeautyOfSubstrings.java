import java.util.*;

public class SumOfBeautyOfSubstrings {

    // ===================================================
    // Beauty of a string = (max frequency char) - (min frequency char)
    // Find sum of beauty of all substrings
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force
    // For each substring, compute character frequencies, find max-min
    // Time: O(N^3) | Space: O(1)
    // ===================================================
    public static int beautySum_Brute(String s) {
        int n = s.length(), totalBeauty = 0;

        for (int i = 0; i < n; i++) {
            int[] freq = new int[26];
            for (int j = i; j < n; j++) {
                freq[s.charAt(j) - 'a']++;
                int maxF = 0, minF = Integer.MAX_VALUE;
                for (int f : freq) {
                    if (f > 0) { maxF = Math.max(maxF, f); minF = Math.min(minF, f); }
                }
                totalBeauty += maxF - minF;
            }
        }
        return totalBeauty;
    }

    // ===================================================
    // Approach 2 - Optimal (Optimized inner loop)
    // Fix start index, expand end index maintaining running freq array
    // Compute max and min from freq[] (size 26) in O(26) = O(1)
    // Time: O(N^2) | Space: O(1)
    // ===================================================
    public static int beautySum(String s) {
        int n = s.length(), totalBeauty = 0;

        for (int i = 0; i < n; i++) {
            int[] freq = new int[26];
            for (int j = i; j < n; j++) {
                freq[s.charAt(j) - 'a']++;

                int maxF = 0, minF = Integer.MAX_VALUE;
                for (int f : freq) {
                    if (f > 0) {
                        maxF = Math.max(maxF, f);
                        minF = Math.min(minF, f);
                    }
                }
                totalBeauty += maxF - minF;
            }
        }
        return totalBeauty;
    }

    public static void main(String[] args) {
        System.out.println(beautySum("aabcb")); // Output: 5
        // Substrings with non-zero beauty:
        // "aab"→1, "aabc"→1, "aabcb"→2, "abcb"→1
        // Total = 5

        System.out.println(beautySum("aabcbaa")); // Output: 17
    }
}
