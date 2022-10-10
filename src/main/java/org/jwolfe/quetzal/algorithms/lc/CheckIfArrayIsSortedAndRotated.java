package org.jwolfe.quetzal.algorithms.lc;

public class CheckIfArrayIsSortedAndRotated {
    class Solution {
        public boolean check(int[] nums) {
            if(nums == null || nums.length < 3) {
                return true;
            }

            int n = nums.length;
            int index = binarySearchForLeast(nums);

            int prev = nums[index];
            for(int i = 1; i < n; i++) {
                int curr = nums[(index + i) % n];
                if(curr < prev) {
                    return false;
                }

                prev = curr;
            }

            return true;
        }

        private int binarySearchForLeast(int[] nums) {
            int n = nums.length;

            if(nums[0] < nums[n - 1]) {
                return 0;
            }

            int left = 0;
            int right = n - 1;
            int end = n - 1;

            while(left <= right) {
                int mid = left + (right - left) / 2;

                if(mid != end && nums[mid] > nums[mid + 1]) {
                    return mid + 1;
                } else if(nums[mid] >= nums[left]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return 0;
        }
    }

    class Solution_Incorrect {
        public boolean check(int[] nums) {
            if(nums == null || nums.length < 3) {
                return true;
            }

            int n = nums.length;
            int prev = nums[0];
            int last = nums[n - 1];
            int falls = 0;

            for(int i = 1; i < n; i++) {
                int curr = nums[i];
                if(curr < prev) {
                    if(prev < last) {
                        return false;
                    }

                    falls++;
                    if(falls > 1) {
                        return false;
                    }
                }

                prev = curr;
            }

            return true;
        }
    }

// [3,4,5,1,2]
// [2,1,3,4]
// [2,4,1,3]
// [1,1,1]
// [6,10,6]
// [7,9,1,1,1,3]
}

//    1752. Check if Array Is Sorted and Rotated
//    Easy
//    Given an array nums, return true if the array was originally sorted in non-decreasing order, then rotated some number of positions (including zero). Otherwise, return false.
//
//    There may be duplicates in the original array.
//
//    Note: An array A rotated by x positions results in an array B of the same length such that A[i] == B[(i+x) % A.length], where % is the modulo operation.
//
//
//
//    Example 1:
//
//    Input: nums = [3,4,5,1,2]
//    Output: true
//    Explanation: [1,2,3,4,5] is the original sorted array.
//    You can rotate the array by x = 3 positions to begin on the the element of value 3: [3,4,5,1,2].
//    Example 2:
//
//    Input: nums = [2,1,3,4]
//    Output: false
//    Explanation: There is no sorted array once rotated that can make nums.
//    Example 3:
//
//    Input: nums = [1,2,3]
//    Output: true
//    Explanation: [1,2,3] is the original sorted array.
//    You can rotate the array by x = 0 positions (i.e. no rotation) to make nums.
//
//
//    Constraints:
//
//    1 <= nums.length <= 100
//    1 <= nums[i] <= 100