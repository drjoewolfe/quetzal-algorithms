package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class MaximumSubarraySumWithLengthDivisibleByK {
    class Solution {
        public long maxSubarraySum(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return 0L;
            }

            long total = 0;
            long[] prefix = new long[k];
            long maxSum = Long.MIN_VALUE;
            Arrays.fill(prefix, Long.MAX_VALUE);
            prefix[0] = 0;
            for (int i = 0; i < nums.length; i++) {
                int val = nums[i];
                total += val;

                int length = i + 1;
                int prefixIndex = length % k;

                if (prefix[prefixIndex] != Long.MAX_VALUE) {
                    maxSum = Math.max(maxSum, total - prefix[prefixIndex]);
                }

                prefix[prefixIndex] = Math.min(prefix[prefixIndex], total);
            }

            return maxSum;
        }
    }
}

//    3381. Maximum Subarray Sum With Length Divisible by K
//    Medium
//    You are given an array of integers nums and an integer k.
//
//    Return the maximum sum of a subarray of nums, such that the size of the subarray is divisible by k.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2], k = 1
//
//    Output: 3
//
//    Explanation:
//
//    The subarray [1, 2] with sum 3 has length equal to 2 which is divisible by 1.
//
//    Example 2:
//
//    Input: nums = [-1,-2,-3,-4,-5], k = 4
//
//    Output: -10
//
//    Explanation:
//
//    The maximum sum subarray is [-1, -2, -3, -4] which has length equal to 4 which is divisible by 4.
//
//    Example 3:
//
//    Input: nums = [-5,1,2,-3,4], k = 2
//
//    Output: 4
//
//    Explanation:
//
//    The maximum sum subarray is [1, 2, -3, 4] which has length equal to 4 which is divisible by 2.
//
//
//
//    Constraints:
//
//    1 <= k <= nums.length <= 2 * 105
//    -109 <= nums[i] <= 109