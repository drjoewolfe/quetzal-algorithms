package org.jwolfe.quetzal.algorithms.lc;

public class GreatestSumDivisibleByThree {
    class Solution {
        public int maxSumDivThree(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int totalSum = 0;
            long smallest_mod1 = Integer.MAX_VALUE;
            long smallest_mod2 = Integer.MAX_VALUE;

            for (int x : nums) {
                totalSum += x;

                if (x % 3 == 1) {
                    smallest_mod2 = Math.min(smallest_mod2, x + smallest_mod1);
                    smallest_mod1 = Math.min(smallest_mod1, x);
                } else if (x % 3 == 2) {
                    smallest_mod1 = Math.min(smallest_mod1, x + smallest_mod2);
                    smallest_mod2 = Math.min(smallest_mod2, x);
                }
            }

            if (totalSum % 3 == 0) {
                return totalSum;
            } else if (totalSum % 3 == 1) {
                return totalSum - (int) smallest_mod1;
            } else {
                return totalSum - (int) smallest_mod2;
            }
        }
    }
}

//    1262. Greatest Sum Divisible by Three
//    Medium
//    Given an integer array nums, return the maximum possible sum of elements of the array such that it is divisible by three.
//
//
//
//    Example 1:
//
//    Input: nums = [3,6,5,1,8]
//    Output: 18
//    Explanation: Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).
//    Example 2:
//
//    Input: nums = [4]
//    Output: 0
//    Explanation: Since 4 is not divisible by 3, do not pick any number.
//    Example 3:
//
//    Input: nums = [1,2,3,4,4]
//    Output: 12
//    Explanation: Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum divisible by 3).
//
//
//    Constraints:
//
//    1 <= nums.length <= 4 * 104
//    1 <= nums[i] <= 104