import java.util.*;

public class ReverseEveryWord {

    // ===================================================
    // Problem: Reverse each individual word in a string
    // (NOT the order of words — just the characters within each word)
    // "Hello World" → "olleH dlroW"
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force
    // Split by space, reverse each word using StringBuilder, join back
    // Time: O(N) | Space: O(N)
    // ===================================================
    public static String reverseWordsBrute(String s) {
        String[] words = s.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            result.append(new StringBuilder(words[i]).reverse());
            if (i < words.length - 1) result.append(" ");
        }
        return result.toString();
    }

    // ===================================================
    // Approach 2 - Optimal (Two Pointer on char array)
    // Convert to char array, find word boundaries using two pointers
    // Reverse each word in-place within those boundaries
    // Time: O(N) | Space: O(N) — for char array
    // ===================================================
    private static void reverseRange(char[] arr, int l, int r) {
        while (l < r) {
            char temp = arr[l]; arr[l] = arr[r]; arr[r] = temp;
            l++; r--;
        }
    }

    public static String reverseWords(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int i = 0;

        while (i < n) {
            int j = i;
            while (j < n && arr[j] != ' ') j++; // find word end
            reverseRange(arr, i, j - 1);         // reverse word in-place
            i = j + 1;                           // move past space
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        System.out.println(reverseWords("Hello World"));
        // Output: "olleH dlroW"

        System.out.println(reverseWords("Let's take LeetCode contest"));
        // Output: "s'teL ekat edoCteeL tsetnoC"

        System.out.println(reverseWords("God Ding"));
        // Output: "doG gniD"
    }
}
