import java.util.*;

public class MaxNestingDepthParentheses {

    // ===================================================
    // Problem: Find the maximum depth of nested parentheses
    // e.g. "(1+(2*3)+((8)/4))+1" → depth = 3
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force
    // Use an actual stack, track max size
    // Time: O(N) | Space: O(N)
    // ===================================================
    public static int maxDepthBrute(String s) {
        Stack<Character> stack = new Stack<>();
        int maxDepth = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
                maxDepth = Math.max(maxDepth, stack.size());
            } else if (c == ')') {
                stack.pop();
            }
        }
        return maxDepth;
    }

    // ===================================================
    // Approach 2 - Optimal (Counter instead of stack)
    // Track current depth with an integer counter
    // Update max at each '('
    // Time: O(N) | Space: O(1)
    // ===================================================
    public static int maxDepth(String s) {
        int depth = 0, maxDepth = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                depth++;
                maxDepth = Math.max(maxDepth, depth);
            } else if (c == ')') {
                depth--;
            }
        }
        return maxDepth;
    }

    public static void main(String[] args) {
        System.out.println(maxDepth("(1+(2*3)+((8)/4))+1")); // Output: 3
        System.out.println(maxDepth("(1)+((2))+(((3)))"));   // Output: 3
        System.out.println(maxDepth("()()"));                 // Output: 1
    }
}
