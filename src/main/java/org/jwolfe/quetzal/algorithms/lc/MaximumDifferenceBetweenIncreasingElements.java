package org.jwolfe.quetzal.algorithms.lc;

public class MaximumDifferenceBetweenIncreasingElements {
    class Solution {
        public int maximumDifference(int[] nums) {
            if(nums == null || nums.length < 2) {
                return -1;
            }

            int n = nums.length;
            int maxDifference = -1;
            int minFromLeft = nums[0];

            for(int i = 1; i < n; i++) {
                int val = nums[i];

                if(val > minFromLeft) {
                    maxDifference = Math.max(maxDifference, val - minFromLeft);
                } else {
                    minFromLeft = val;
                }
            }

            return maxDifference;
        }
    }

    class Solution_Correct_1 {
        public int maximumDifference(int[] nums) {
            if(nums == null || nums.length < 2) {
                return -1;
            }

            int n = nums.length;
            int[] largestFromRight = new int[n];
            largestFromRight[n - 1] = nums[n - 1];

            for(int i = n - 2; i >= 0; i--) {
                largestFromRight[i] = Math.max(largestFromRight[i + 1], nums[i]);
            }

            int maxDifference = -1;
            for(int i = 0; i < n - 1; i++) {
                int val = nums[i];

                if(largestFromRight[i + 1] > val) {
                    maxDifference = Math.max(maxDifference, largestFromRight[i + 1] - val);
                }
            }

            return maxDifference;
        }
    }
}

//    2016. Maximum Difference Between Increasing Elements
//    Easy
//    Given a 0-indexed integer array nums of size n, find the maximum difference between nums[i] and nums[j] (i.e., nums[j] - nums[i]), such that 0 <= i < j < n and nums[i] < nums[j].
//
//    Return the maximum difference. If no such i and j exists, return -1.
//
//
//
//    Example 1:
//
//    Input: nums = [7,1,5,4]
//    Output: 4
//    Explanation:
//    The maximum difference occurs with i = 1 and j = 2, nums[j] - nums[i] = 5 - 1 = 4.
//    Note that with i = 1 and j = 0, the difference nums[j] - nums[i] = 7 - 1 = 6, but i > j, so it is not valid.
//    Example 2:
//
//    Input: nums = [9,4,3,2]
//    Output: -1
//    Explanation:
//    There is no i and j such that i < j and nums[i] < nums[j].
//    Example 3:
//
//    Input: nums = [1,5,2,10]
//    Output: 9
//    Explanation:
//    The maximum difference occurs with i = 0 and j = 3, nums[j] - nums[i] = 10 - 1 = 9.
//
//
//    Constraints:
//
//    n == nums.length
//    2 <= n <= 1000
//    1 <= nums[i] <= 109