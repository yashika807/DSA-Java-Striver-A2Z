/*
Problem:
Check whether the given array is sorted in non-decreasing order.

Brute Force:
Traverse the array and compare every element with its previous element.
If any element is smaller than the previous one, the array is not sorted.

Time Complexity: O(N)
Space Complexity: O(1)

Optimal Approach:
The brute force approach is already optimal because every element must be
checked at least once in the worst case.

Time Complexity: O(N)
Space Complexity: O(1)
*/

class Solution {
    public boolean isSorted(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                return false;
            }
        }
        return true;
    }
}
