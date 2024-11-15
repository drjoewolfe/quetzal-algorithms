package org.jwolfe.quetzal.algorithms.lc;

public class ShortestSubarrayToBeRemovedToMakeArraySorted {
    class Solution {
        public int findLengthOfShortestSubarray(int[] arr) {
            if (arr == null || arr.length < 2) {
                return 0;
            }

            int n = arr.length;

            int left = 0;
            int right = n - 1;

            while (right > 0 && arr[right - 1] <= arr[right]) {
                right--;
            }

            int minLength = right;
            while (left < right
                    && (left == 0 || arr[left - 1] <= arr[left])) {
                while (right < n && arr[left] > arr[right]) {
                    right++;
                }

                minLength = Math.min(minLength, right - left - 1);
                left++;
            }

            return minLength;
        }
    }

    class Solution_Correct_1 {
        public int findLengthOfShortestSubarray(int[] arr) {
            if (arr == null || arr.length < 2) {
                return 0;
            }

            int n = arr.length;
            int s = 0;
            while (s < n - 1 && arr[s] <= arr[s + 1]) {
                s++;
            }

            if (s == n - 1) {
                return 0;
            }

            int e = n - 1;
            while (e >= s && arr[e] >= arr[e - 1]) {
                e--;
            }

            int i = 0;
            int j = e;
            int minLength = Math.min(n - 1 - s, e);
            while (i <= s && j < n) {
                if (arr[i] <= arr[j]) {
                    minLength = Math.min(minLength, j - i - 1);
                    i++;
                } else {
                    j++;
                }
            }

            return minLength;
        }
    }

// [1,2,3,10,4,2,3,5]
// [5,4,3,2,1]
// [1,2,3]
// [2,2,2,1,1,1]
}

//    1574. Shortest Subarray to be Removed to Make Array Sorted
//    Medium
//    Given an integer array arr, remove a subarray (can be empty) from arr such that the remaining elements in arr are non-decreasing.
//
//    Return the length of the shortest subarray to remove.
//
//    A subarray is a contiguous subsequence of the array.
//
//
//
//    Example 1:
//
//    Input: arr = [1,2,3,10,4,2,3,5]
//    Output: 3
//    Explanation: The shortest subarray we can remove is [10,4,2] of length 3. The remaining elements after that will be [1,2,3,3,5] which are sorted.
//    Another correct solution is to remove the subarray [3,10,4].
//    Example 2:
//
//    Input: arr = [5,4,3,2,1]
//    Output: 4
//    Explanation: Since the array is strictly decreasing, we can only keep a single element. Therefore we need to remove a subarray of length 4, either [5,4,3,2] or [4,3,2,1].
//    Example 3:
//
//    Input: arr = [1,2,3]
//    Output: 0
//    Explanation: The array is already non-decreasing. We do not need to remove any elements.
//
//
//    Constraints:
//
//    1 <= arr.length <= 105
//    0 <= arr[i] <= 109