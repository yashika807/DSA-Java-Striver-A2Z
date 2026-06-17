import java.util.*;

public class IsomorphicStrings {

    // ===================================================
    // Two strings are isomorphic if characters in s can be
    // replaced to get t — with a consistent one-to-one mapping.
    // No two characters in s can map to the same character in t.
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force
    // For each character in s, check consistency of its mapping
    // using nested loop comparison
    // Time: O(N^2) | Space: O(1)
    // ===================================================
    public static boolean isIsomorphicBrute(String s, String t) {
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < i; j++) {
                // same char in s must map to same char in t, and vice versa
                if ((s.charAt(j) == s.charAt(i)) != (t.charAt(j) == t.charAt(i)))
                    return false;
            }
        }
        return true;
    }

    // ===================================================
    // Approach 2 - Optimal (Two HashMaps)
    // Map s→t and t→s simultaneously
    // If a mapping conflict is found → not isomorphic
    // Time: O(N) | Space: O(1)  [at most 256 unique chars]
    // ===================================================
    public static boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> sToT = new HashMap<>();
        HashMap<Character, Character> tToS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i), tc = t.charAt(i);

            if (sToT.containsKey(sc) && sToT.get(sc) != tc) return false;
            if (tToS.containsKey(tc) && tToS.get(tc) != sc) return false;

            sToT.put(sc, tc);
            tToS.put(tc, sc);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isIsomorphic("egg", "add"));  // Output: true
        System.out.println(isIsomorphic("foo", "bar"));  // Output: false
        System.out.println(isIsomorphic("paper", "title")); // Output: true
    }
}
