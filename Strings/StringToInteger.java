import java.util.*;

public class StringToInteger {

    // ===================================================
    // Rules (mimicking C's atoi):
    // 1. Skip leading whitespace
    // 2. Optional '+' or '-' sign
    // 3. Read digits until non-digit or end
    // 4. Clamp to [Integer.MIN_VALUE, Integer.MAX_VALUE]
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force
    // Handle each step explicitly using string operations and try-catch
    // Time: O(N) | Space: O(N)
    // ===================================================
    public static int myAtoiBrute(String s) {
        s = s.stripLeading(); // strip leading whitespace
        if (s.isEmpty()) return 0;

        int sign = 1;
        int idx = 0;

        if (s.charAt(0) == '-') { sign = -1; idx = 1; }
        else if (s.charAt(0) == '+') { idx = 1; }

        StringBuilder digits = new StringBuilder();
        while (idx < s.length() && Character.isDigit(s.charAt(idx)))
            digits.append(s.charAt(idx++));

        if (digits.length() == 0) return 0;

        try {
            long val = Long.parseLong(digits.toString()) * sign;
            if (val > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (val < Integer.MIN_VALUE) return Integer.MIN_VALUE;
            return (int) val;
        } catch (NumberFormatException e) {
            return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
    }

    // ===================================================
    // Approach 2 - Optimal (Single Pass, no extra string)
    // Build result digit by digit while checking overflow
    // Clamp to INT bounds before each multiplication to avoid overflow
    // Time: O(N) | Space: O(1)
    // ===================================================
    public static int myAtoi(String s) {
        int n = s.length(), i = 0;
        int result = 0, sign = 1;

        // Step 1: skip leading whitespace
        while (i < n && s.charAt(i) == ' ') i++;

        // Step 2: read sign
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = s.charAt(i) == '-' ? -1 : 1;
            i++;
        }

        // Step 3: read digits, handle overflow
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';

            // Check if result would overflow before multiplying
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        return result * sign;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("42"));           // Output: 42
        System.out.println(myAtoi("   -42"));        // Output: -42
        System.out.println(myAtoi("4193 with words")); // Output: 4193
        System.out.println(myAtoi("99999999999"));  // Output: 2147483647 (clamped)
    }
}
