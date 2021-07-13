package org.jwolfe.quetzal.algorithms.lc;

public class FindPeakElement {
    class Solution {
        public int findPeakElement(int[] nums) {
            if(nums == null || nums.length == 0) {
                return -1;
            }

            int left = 0;
            int right = nums.length - 1;
            return findPeakElement(nums, left, right);
        }

        public int findPeakElement(int[] nums, int left, int right) {
            if(left == right) {
                return left;
            }

            int mid = left + (right - left) / 2;

            int a = nums[mid];
            int b = nums[mid + 1];

            if(a > b) {
                // Downward slope
                return findPeakElement(nums, left, mid);
            } else {
                // Upward slope
                return findPeakElement(nums, mid + 1, right);
            }
        }
    }

    class Solution_BinarySearchIterative {
        public int findPeakElement(int[] nums) {
            if(nums == null || nums.length == 0) {
                return -1;
            }

            int left = 0;
            int right = nums.length - 1;

            while(left < right) {
                int mid = left + (right - left) / 2;

                int a = nums[mid];
                int b = nums[mid + 1];

                if(a > b) {
                    // Downward slope
                    right = mid;
                } else {
                    // Upward slope
                    left = mid + 1;
                }
            }

            return left;
        }
    }

    class Solution_Linear {
        public int findPeakElement(int[] nums) {
            if(nums == null || nums.length == 0) {
                return -1;
            }

            int n = nums.length;
            for(int i = 0; i < n - 1; i++) {
                if(nums[i] > nums[i + 1]) {
                    return i;
                }
            }

            return n - 1;
        }
    }

    class Solution_Classic {
        public int findPeakElement(int[] nums) {
            if(nums == null || nums.length == 0) {
                return -1;
            }

            int n = nums.length;
            for(int i = 0; i < n; i++) {

                double a = (i == 0) ? Double.NEGATIVE_INFINITY : nums[i - 1];
                double b = nums[i];
                double c = (i == n - 1) ? Double.NEGATIVE_INFINITY : nums[i + 1];

                if(a < b && b > c) {
                    return i;
                }
            }

            return -1;
        }
    }

    class Solution_Correct_Classic_2 {
        public int findPeakElement(int[] nums) {
            if(nums == null || nums.length == 0) {
                return -1;
            }

            int n = nums.length;
            if(n == 1) {
                return 0;
            }

            int prev = Integer.MIN_VALUE;
            for(int i = 0; i < n; i++) {
                int curr = nums[i];
                int next = (i < n - 1) ? nums[i + 1] : Integer.MIN_VALUE;

                if(curr > prev && curr > next) {
                    return i;
                }

                prev = curr;
            }

            return -1;
        }
    }
}

//    162. Find Peak Element
//    Medium
//
//    3358
//
//    2841
//
//    Add to List
//
//    Share
//    A peak element is an element that is strictly greater than its neighbors.
//
//    Given an integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
//
//    You may imagine that nums[-1] = nums[n] = -∞.
//
//    You must write an algorithm that runs in O(log n) time.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,3,1]
//    Output: 2
//    Explanation: 3 is a peak element and your function should return the index number 2.
//    Example 2:
//
//    Input: nums = [1,2,1,3,5,6,4]
//    Output: 5
//    Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
//
//
//    Constraints:
//
//    1 <= nums.length <= 1000
//    -231 <= nums[i] <= 231 - 1
//    nums[i] != nums[i + 1] for all valid i