import java.util.*;

public class CountNumberOfSubstrings {

    // ===================================================
    // Problem: Count substrings that contain all 3 vowels (a, e, i, o, u)
    // at least once, and may contain any number of consonants
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force
    // Generate all substrings, check if each contains all 5 vowels
    // Time: O(N^2) | Space: O(1)
    // ===================================================
    public static int countSubstringsBrute(String s) {
        int count = 0;
        int n = s.length();
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');

        for (int i = 0; i < n; i++) {
            Set<Character> seen = new HashSet<>();
            for (int j = i; j < n; j++) {
                if (vowels.contains(s.charAt(j))) seen.add(s.charAt(j));
                if (seen.size() == 5) count++;
            }
        }
        return count;
    }

    // ===================================================
    // Approach 2 - Optimal (Sliding Window — count at least K)
    // Use the formula:
    //   count(at least 5 vowels) = count(at least 1) - count(at least 6)
    // where count(at least k) = number of substrings with >= k distinct vowels
    //
    // Helper: countAtLeast(s, k) counts substrings with >= k distinct vowels
    // using a sliding window
    //
    // Time: O(N) | Space: O(1)
    // ===================================================
    private static int countAtLeast(String s, int k) {
        int[] freq = new int[26];
        int vowelCount = 0, left = 0, count = 0;
        Set<Integer> vowelIdx = Set.of('a'-'a', 'e'-'a', 'i'-'a', 'o'-'a', 'u'-'a');

        for (int right = 0; right < s.length(); right++) {
            int ci = s.charAt(right) - 'a';
            if (vowelIdx.contains(ci)) {
                if (freq[ci] == 0) vowelCount++;
                freq[ci]++;
            }

            while (vowelCount >= k) {
                count += s.length() - right; // all substrings ending at or after right
                int li = s.charAt(left) - 'a';
                if (vowelIdx.contains(li)) {
                    freq[li]--;
                    if (freq[li] == 0) vowelCount--;
                }
                left++;
            }
        }
        return count;
    }

    public static int countSubstrings(String s) {
        return countAtLeast(s, 5) - countAtLeast(s, 6);
    }

    public static void main(String[] args) {
        System.out.println(countSubstrings("aeiou"));       // Output: 1
        System.out.println(countSubstrings("aeiouaeiou"));  // Output: 36
        System.out.println(countSubstrings("aeoiu"));       // Output: 1
    }
}
