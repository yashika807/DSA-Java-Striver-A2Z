import java.util.*;

public class SortCharactersByFrequency {

    // ===================================================
    // Approach 1 - Brute Force
    // Count frequency, sort characters by frequency descending
    // Build result string
    // Time: O(N log N) | Space: O(N)
    // ===================================================
    public static String frequencySortBrute(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) freq.put(c, freq.getOrDefault(c, 0) + 1);

        List<Character> chars = new ArrayList<>(freq.keySet());
        chars.sort((a, b) -> freq.get(b) - freq.get(a)); // sort by freq desc

        StringBuilder result = new StringBuilder();
        for (char c : chars)
            result.append(String.valueOf(c).repeat(freq.get(c)));
        return result.toString();
    }

    // ===================================================
    // Approach 2 - Optimal (Bucket Sort)
    // Frequency can be at most N → use bucket array of size N+1
    // Each bucket holds all characters with that frequency
    // Traverse buckets from high to low
    // Time: O(N) | Space: O(N)
    // ===================================================
    public static String frequencySort(String s) {
        int n = s.length();
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) freq.put(c, freq.getOrDefault(c, 0) + 1);

        // bucket[i] = list of characters with frequency i
        List<List<Character>> buckets = new ArrayList<>();
        for (int i = 0; i <= n; i++) buckets.add(new ArrayList<>());

        for (Map.Entry<Character, Integer> entry : freq.entrySet())
            buckets.get(entry.getValue()).add(entry.getKey());

        StringBuilder result = new StringBuilder();
        for (int i = n; i >= 1; i--) {
            for (char c : buckets.get(i))
                result.append(String.valueOf(c).repeat(i));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(frequencySort("tree"));   // Output: "eert" or "eetr"
        System.out.println(frequencySort("cccaaa")); // Output: "cccaaa" or "aaaccc"
        System.out.println(frequencySort("Aabb"));   // Output: "bbAa" or "bbaA"
    }
}
