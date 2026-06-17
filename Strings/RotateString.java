import java.util.*;

public class RotateString {

    // ===================================================
    // Problem: Check if string s can become goal
    // after some number of left rotations
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force
    // Simulate all N rotations, check if any equals goal
    // Time: O(N^2) | Space: O(N)
    // ===================================================
    public static boolean rotateStringBrute(String s, String goal) {
        if (s.length() != goal.length()) return false;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            // rotate left by i: take s[i..n-1] + s[0..i-1]
            String rotated = s.substring(i) + s.substring(0, i);
            if (rotated.equals(goal)) return true;
        }
        return false;
    }

    // ===================================================
    // Approach 2 - Optimal (String Concatenation Trick)
    // Key insight: all rotations of s are substrings of s+s
    // So just check if goal is a substring of s+s
    // Time: O(N^2) with contains (or O(N) with KMP) | Space: O(N)
    // ===================================================
    public static boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) return false;
        return (s + s).contains(goal);
    }

    public static void main(String[] args) {
        System.out.println(rotateString("abcde", "cdeab")); // Output: true
        System.out.println(rotateString("abcde", "abced")); // Output: false
    }
}
