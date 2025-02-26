package org.jwolfe.quetzal.algorithms.lc;

public class MaximumAbsoluteSumOfAnySubarray {
    class Solution {
        public int maxAbsoluteSum(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int maxAbsSum = 0;
            int prefixSum = 0;

            int maxPrefixSum = Integer.MIN_VALUE;
            int minPrefixSum = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                int val = nums[i];
                prefixSum += val;

                maxPrefixSum = Math.max(maxPrefixSum, prefixSum);
                minPrefixSum = Math.min(minPrefixSum, prefixSum);

                if (prefixSum > 0) {
                    maxAbsSum = Math.max(maxAbsSum,
                            Math.max(prefixSum, prefixSum - minPrefixSum));
                } else {
                    maxAbsSum = Math.max(maxAbsSum,
                            Math.max(Math.abs(prefixSum), Math.abs(prefixSum - maxPrefixSum)));
                }
            }

            return maxAbsSum;
        }
    }
}

//    1749. Maximum Absolute Sum of Any Subarray
//    Medium
//    You are given an integer array nums. The absolute sum of a subarray [numsl, numsl+1, ..., numsr-1, numsr] is abs(numsl + numsl+1 + ... + numsr-1 + numsr).
//
//    Return the maximum absolute sum of any (possibly empty) subarray of nums.
//
//    Note that abs(x) is defined as follows:
//
//    If x is a negative integer, then abs(x) = -x.
//    If x is a non-negative integer, then abs(x) = x.
//
//
//    Example 1:
//
//    Input: nums = [1,-3,2,3,-4]
//    Output: 5
//    Explanation: The subarray [2,3] has absolute sum = abs(2+3) = abs(5) = 5.
//    Example 2:
//
//    Input: nums = [2,-5,1,-4,3,-2]
//    Output: 8
//    Explanation: The subarray [-5,1,-4] has absolute sum = abs(-5+1-4) = abs(-8) = 8.
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    -104 <= nums[i] <= 104
