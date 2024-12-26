package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class TargetSum {
    class Solution {
        public int findTargetSumWays(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int total = 0;
            for (int val : nums) {
                total += val;
            }

            int[][] memo = new int[nums.length][2 * total + 1];
            for (int[] row : memo) {
                Arrays.fill(row, Integer.MIN_VALUE);
            }

            return findTargetSumWays(nums, target, 0, 0, memo, total);
        }

        private int findTargetSumWays(int[] nums, int target, int current, int index, int[][] memo, int total) {
            if (index == nums.length) {
                if (current == target) {
                    return 1;
                } else {
                    return 0;
                }
            }

            if (memo[index][total + current] != Integer.MIN_VALUE) {
                return memo[index][total + current];
            }

            int addWays = findTargetSumWays(nums, target, current + nums[index], index + 1, memo, total);
            int subtractWays = findTargetSumWays(nums, target, current - nums[index], index + 1, memo, total);

            return memo[index][total + current] = addWays + subtractWays;
        }
    }

    class Solution_Correct_1 {
        public int findTargetSumWays(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            return findTargetSumWays(nums, target, 0, 0);
        }

        private int findTargetSumWays(int[] nums, int target, int current, int index) {
            if (index == nums.length) {
                if (current == target) {
                    return 1;
                } else {
                    return 0;
                }
            }

            int addWays = findTargetSumWays(nums, target, current + nums[index], index + 1);
            int subtractWays = findTargetSumWays(nums, target, current - nums[index], index + 1);

            return addWays + subtractWays;
        }
    }
}

//    494. Target Sum
//    Medium
//    You are given an integer array nums and an integer target.
//
//    You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.
//
//    For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
//    Return the number of different expressions that you can build, which evaluates to target.
//
//
//
//    Example 1:
//
//    Input: nums = [1,1,1,1,1], target = 3
//    Output: 5
//    Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
//    -1 + 1 + 1 + 1 + 1 = 3
//    +1 - 1 + 1 + 1 + 1 = 3
//    +1 + 1 - 1 + 1 + 1 = 3
//    +1 + 1 + 1 - 1 + 1 = 3
//    +1 + 1 + 1 + 1 - 1 = 3
//    Example 2:
//
//    Input: nums = [1], target = 1
//    Output: 1
//
//
//    Constraints:
//
//    1 <= nums.length <= 20
//    0 <= nums[i] <= 1000
//    0 <= sum(nums[i]) <= 1000
//    -1000 <= target <= 1000