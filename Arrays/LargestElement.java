/*
Problem: Largest Element in an Array

Approach:
Traverse the array once while maintaining the maximum element.

Time Complexity: O(n)
Space Complexity: O(1)

Source: Striver A2Z
*/
class Solution {
    public static int largest(int[] arr) {
        // code here
        int largest = arr[0];
        for(int i=1;i<arr.length;i++){
            if(arr[i]>largest){
                largest=arr[i];
            }
        }
        return largest;
    }
}
