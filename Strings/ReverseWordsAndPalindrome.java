import java.util.*;

public class ReverseWordsAndPalindrome {

    // ===================================================
    // PART 1: Reverse Words in a Given String
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force
    // Split by spaces, reverse the array, join back
    // Time: O(N) | Space: O(N)
    // ===================================================
    public static String reverseWordsBrute(String s) {
        String[] words = s.trim().split("\\s+"); // handles multiple spaces
        StringBuilder result = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);
            if (i != 0) result.append(" ");
        }
        return result.toString();
    }

    // ===================================================
    // Approach 2 - Optimal (Two Pointer / Deque)
    // Traverse string, extract words manually, push to front of deque
    // Time: O(N) | Space: O(N)
    // ===================================================
    public static String reverseWords(String s) {
        int n = s.length();
        Deque<String> deque = new ArrayDeque<>();
        int i = 0;

        while (i < n) {
            while (i < n && s.charAt(i) == ' ') i++; // skip spaces
            if (i >= n) break;
            int j = i;
            while (j < n && s.charAt(j) != ' ') j++; // find word end
            deque.addFirst(s.substring(i, j));         // push to front
            i = j;
        }
        return String.join(" ", deque);
    }

    // ===================================================
    // PART 2: Palindrome Check
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force
    // Reverse the string, compare with original
    // Time: O(N) | Space: O(N)
    // ===================================================
    public static boolean isPalindromeBrute(String s) {
        String reversed = new StringBuilder(s).reverse().toString();
        return s.equals(reversed);
    }

    // ===================================================
    // Approach 2 - Optimal (Two Pointers)
    // Compare characters from both ends moving inward
    // Time: O(N) | Space: O(1)
    // ===================================================
    public static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        // Reverse words
        System.out.println(reverseWords("  the sky is blue  "));
        // Output: "blue is sky the"

        // Palindrome
        System.out.println(isPalindrome("racecar")); // Output: true
        System.out.println(isPalindrome("hello"));   // Output: false
    }
}
