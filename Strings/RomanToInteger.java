import java.util.*;

public class RomanToInteger {

    // ===================================================
    // Roman numeral rules:
    // I=1, V=5, X=10, L=50, C=100, D=500, M=1000
    // Subtraction rule: if a smaller value precedes a larger one → subtract it
    // e.g. IV = 4, IX = 9, XL = 40, XC = 90, CD = 400, CM = 900
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force
    // Check every two-character combination for subtraction cases
    // Use if-else chain to handle all 6 special pairs
    // Time: O(N) | Space: O(1)
    // ===================================================
    public static int romanToIntBrute(String s) {
        int result = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (i + 1 < n && valueOf(s.charAt(i)) < valueOf(s.charAt(i + 1)))
                result -= valueOf(s.charAt(i));
            else
                result += valueOf(s.charAt(i));
        }
        return result;
    }

    private static int valueOf(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

    // ===================================================
    // Approach 2 - Optimal (HashMap + Single Pass)
    // Store values in map, traverse right to left
    // If current value < previous seen value → subtract; else add
    // Time: O(N) | Space: O(1)
    // ===================================================
    public static int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);   map.put('V', 5);   map.put('X', 10);
        map.put('L', 50);  map.put('C', 100); map.put('D', 500);
        map.put('M', 1000);

        int result = 0, prev = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            int curr = map.get(s.charAt(i));
            if (curr < prev) result -= curr; // subtraction case
            else             result += curr;
            prev = curr;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("III"));     // Output: 3
        System.out.println(romanToInt("LVIII"));   // Output: 58
        System.out.println(romanToInt("MCMXCIV")); // Output: 1994
    }
}
