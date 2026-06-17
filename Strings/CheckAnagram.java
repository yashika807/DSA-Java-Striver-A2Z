import java.util.*;

public class CheckAnagram {

    // ===================================================
    // Two strings are anagrams if one is a rearrangement of the other
    // i.e., they have the same character frequencies
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force (Sort and Compare)
    // Sort both strings, compare if equal
    // Time: O(N log N) | Space: O(N)
    // ===================================================
    public static boolean isAnagramBrute(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] a = s.toCharArray();
        char[] b = t.toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);
        return Arrays.equals(a, b);
    }

    // ===================================================
    // Approach 2 - Better (HashMap frequency count)
    // Count frequency of each char in s, decrement for t
    // If any count != 0 → not anagram
    // Time: O(N) | Space: O(N)
    // ===================================================
    public static boolean isAnagramBetter(String s, String t) {
        if (s.length() != t.length()) return false;
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) - 1);
            if (map.get(c) < 0) return false;
        }
        return true;
    }

    // ===================================================
    // Approach 3 - Optimal (Frequency array for lowercase letters)
    // Use int[26] instead of HashMap — constant space
    // Time: O(N) | Space: O(1)
    // ===================================================
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;
        for (char c : t.toCharArray()) {
            freq[c - 'a']--;
            if (freq[c - 'a'] < 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram")); // Output: true
        System.out.println(isAnagram("rat", "car"));         // Output: false
    }
}
