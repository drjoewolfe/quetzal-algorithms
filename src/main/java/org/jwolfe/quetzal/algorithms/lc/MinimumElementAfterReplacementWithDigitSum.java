package org.jwolfe.quetzal.algorithms.lc;

public class MinimumElementAfterReplacementWithDigitSum {
    class Solution {
        public int minElement(int[] nums) {
            if (nums == null || nums.length == 0) {
                return -1;
            }

            int minSum = Integer.MAX_VALUE;
            for (int val : nums) {
                int sum = digitSum(val);
                minSum = Math.min(minSum, sum);
            }

            return minSum;
        }

        private int digitSum(int val) {
            int sum = 0;
            while (val > 0) {
                sum += val % 10;
                val /= 10;
            }

            return sum;
        }
    }
}

//    3300. Minimum Element After Replacement With Digit Sum
//    Easy
//    You are given an integer array nums.
//
//    You replace each element in nums with the sum of its digits.
//
//    Return the minimum element in nums after all replacements.
//
//
//
//    Example 1:
//
//    Input: nums = [10,12,13,14]
//
//    Output: 1
//
//    Explanation:
//
//    nums becomes [1, 3, 4, 5] after all replacements, with minimum element 1.
//
//    Example 2:
//
//    Input: nums = [1,2,3,4]
//
//    Output: 1
//
//    Explanation:
//
//    nums becomes [1, 2, 3, 4] after all replacements, with minimum element 1.
//
//    Example 3:
//
//    Input: nums = [999,19,199]
//
//    Output: 10
//
//    Explanation:
//
//    nums becomes [27, 10, 19] after all replacements, with minimum element 10.
//
//
//
//    Constraints:
//
//    1 <= nums.length <= 100
//    1 <= nums[i] <= 104