import java.util.*;

public class BookAllocation {

    // ===================================================
    // Problem: Allocate N books to M students such that
    // each student gets at least one book (contiguous),
    // and the maximum pages assigned to any student is minimized
    // ===================================================

    // ===================================================
    // Approach 1 - Brute Force
    // Try every possible max page limit from max(pages) to sum(pages)
    // Return first limit where M students can be allocated
    // Time: O((sum - max) * N) | Space: O(1)
    // ===================================================
    public static int allocateBrute(int[] pages, int m) {
        if (m > pages.length) return -1;
        int low = Arrays.stream(pages).max().getAsInt();
        int high = Arrays.stream(pages).sum();
        for (int limit = low; limit <= high; limit++) {
            if (countStudents(pages, limit) <= m) return limit;
        }
        return high;
    }

    // ===================================================
    // Approach 2 - Optimal (Binary Search on Answer)
    // Search space: [max(pages), sum(pages)]
    // If students needed with limit=mid <= m → valid, try smaller (go left)
    // Else → go right
    // Time: O(N * log(sum - max)) | Space: O(1)
    // ===================================================
    private static int countStudents(int[] pages, int maxPages) {
        int students = 1, pagesSum = 0;
        for (int p : pages) {
            if (pagesSum + p > maxPages) {
                students++;   // assign to next student
                pagesSum = 0;
            }
            pagesSum += p;
        }
        return students;
    }

    public static int allocate(int[] pages, int m) {
        if (m > pages.length) return -1;
        int low = Arrays.stream(pages).max().getAsInt();
        int high = Arrays.stream(pages).sum();
        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (countStudents(pages, mid) <= m) {
                ans = mid;
                high = mid - 1; // try smaller limit
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] pages = {12, 34, 67, 90};
        System.out.println(allocate(pages, 2));  // Output: 113
    }
}
