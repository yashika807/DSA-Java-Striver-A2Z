import java.util.*;

public class LongestPalindromicSubstring {

    // ===================================================
    // Approach 1 - Brute Force
    // Check all substrings, verify if palindrome using two pointers
    // Time: O(N^3) | Space: O(1)
    // ===================================================
    public static String longestPalindromeBrute(String s) {
        int n = s.length();
        String result = s.substring(0, 1);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isPalindrome(s, i, j) && j - i + 1 > result.length())
                    result = s.substring(i, j + 1);
            }
        }
        return result;
    }

    private static boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }

    // ===================================================
    // Approach 2 - Better (Dynamic Programming)
    // dp[i][j] = true if s[i..j] is palindrome
    // Base: all length-1 and length-2 substrings
    // Transition: dp[i][j] = (s[i]==s[j]) && dp[i+1][j-1]
    // Time: O(N^2) | Space: O(N^2)
    // ===================================================
    public static String longestPalindromeDP(String s) {
        int n = s.length(), start = 0, maxLen = 1;
        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) dp[i][i] = true;

        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i; maxLen = 2;
            }
        }

        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    if (len > maxLen) { maxLen = len; start = i; }
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    // ===================================================
    // Approach 3 - Optimal (Expand Around Center)
    // For each center (N single + N-1 double centers), expand outward
    // Track the longest palindrome found
    // Time: O(N^2) | Space: O(1)
    // ===================================================
    public static String longestPalindrome(String s) {
        int n = s.length(), start = 0, maxLen = 1;

        for (int i = 0; i < n; i++) {
            // odd length palindromes
            int l = i, r = i;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) { l--; r++; }
            if (r - l - 1 > maxLen) { maxLen = r - l - 1; start = l + 1; }

            // even length palindromes
            l = i; r = i + 1;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) { l--; r++; }
            if (r - l - 1 > maxLen) { maxLen = r - l - 1; start = l + 1; }
        }
        return s.substring(start, start + maxLen);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad")); // Output: "bab" or "aba"
        System.out.println(longestPalindrome("cbbd"));  // Output: "bb"
        System.out.println(longestPalindrome("racecar")); // Output: "racecar"
    }
}
