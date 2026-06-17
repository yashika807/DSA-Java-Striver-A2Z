import java.util.*;

public class RemoveOutermostParentheses {

    // ===================================================
    // Approach 1 - Brute Force
    // Find each primitive decomposition by tracking depth
    // Collect characters that are NOT outermost open/close
    // Time: O(N) | Space: O(N)
    // ===================================================
    public static String removeOuterParenthesesBrute(String s) {
        StringBuilder result = new StringBuilder();
        int depth = 0, start = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') depth++;
            else depth--;

            if (depth == 0) {
                // primitive found: s[start..i], strip outer brackets
                result.append(s, start + 1, i);
                start = i + 1;
            }
        }
        return result.toString();
    }

    // ===================================================
    // Approach 2 - Optimal (Single Pass with counter)
    // Track open count:
    //   '(' → if open > 0 before incrementing, it's NOT outermost → include it
    //   ')' → if open > 1 before decrementing, it's NOT outermost → include it
    // Time: O(N) | Space: O(N)
    // ===================================================
    public static String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int open = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                if (open > 0) result.append(c); // not outermost open
                open++;
            } else {
                open--;
                if (open > 0) result.append(c); // not outermost close
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeOuterParentheses("(()())(())"));
        // Output: "()()()"

        System.out.println(removeOuterParentheses("(()())(())(()(()))"));
        // Output: "()()()()(())"
    }
}
