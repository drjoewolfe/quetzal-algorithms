package org.jwolfe.quetzal.algorithms.lc;

public class MaximumSubarray {
    class Solution {
        public int maxSubArray(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int maxSum = Integer.MIN_VALUE;
            int runningSum = 0;

            for(int i = 0; i < n; i++) {
                int val = nums[i];
                runningSum += val;
                maxSum = Math.max(maxSum, runningSum);

                if(runningSum < 0) {
                    runningSum = 0;
                }
            }

            return maxSum;
        }
    }

    class Solution_Correct_1 {
        public int maxSubArray(int[] nums) {
            int maxSum = Integer.MIN_VALUE;

            if(nums == null || nums.length == 0) {
                return maxSum;
            }

            int runningSum = 0;

            for(int i = 0; i < nums.length; i++) {
                runningSum += nums[i];
                maxSum = Math.max(maxSum, runningSum);

                if(runningSum < 0) {
                    runningSum = 0;
                }
            }

            return maxSum;
        }
    }

// [-2,1,-3,4,-1,2,1,-5,4]
}

//    53. Maximum Subarray
//    Easy
//    Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
//
//    A subarray is a contiguous part of an array.
//
//
//
//    Example 1:
//
//    Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
//    Output: 6
//    Explanation: [4,-1,2,1] has the largest sum = 6.
//    Example 2:
//
//    Input: nums = [1]
//    Output: 1
//    Example 3:
//
//    Input: nums = [5,4,-1,7,8]
//    Output: 23
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    -104 <= nums[i] <= 104
//
//
//    Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.