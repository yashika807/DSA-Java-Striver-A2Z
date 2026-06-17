import java.util.*;

public class LargestOddNumberInString {

    // ===================================================
    // Problem: Given a numeric string, find the largest-valued
    // odd number that is a contiguous prefix of the string.
    // Return "" if no odd prefix exists.
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force
    // Check all prefixes from longest to shortest
    // Return the first one that ends in an odd digit
    // Time: O(N) | Space: O(1)
    // ===================================================
    public static String largestOddNumberBrute(String num) {
        for (int i = num.length() - 1; i >= 0; i--) {
            if ((num.charAt(i) - '0') % 2 != 0)
                return num.substring(0, i + 1);
        }
        return "";
    }

    // ===================================================
    // Approach 2 - Optimal (Same idea, cleaner)
    // Scan from right → first odd digit found = answer boundary
    // A number is odd iff its last digit is odd
    // So the longest valid prefix ends at the rightmost odd digit
    // Time: O(N) | Space: O(1)
    // ===================================================
    public static String largestOddNumber(String num) {
        for (int i = num.length() - 1; i >= 0; i--) {
            if ((num.charAt(i) - '0') % 2 == 1)
                return num.substring(0, i + 1);
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(largestOddNumber("52"));     // Output: "5"
        System.out.println(largestOddNumber("4206"));   // Output: ""
        System.out.println(largestOddNumber("35427"));  // Output: "35427"
    }
}
