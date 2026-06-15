/*
Problem: Second Largest Element

Approach:
Maintain largest and second largest while traversing the array.

Time Complexity: O(n)
Space Complexity: O(1)

Source: Striver A2Z
*/
public class SecondLargest {
    public static int getSecondLargest(int[] arr) {
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > largest) {
                secondLargest = largest;
                largest = arr[i];
            } else if (arr[i] > secondLargest && arr[i] != largest) {
                secondLargest = arr[i];
            }
        }
        return secondLargest == Integer.MIN_VALUE ? -1 : secondLargest;
    }
}
