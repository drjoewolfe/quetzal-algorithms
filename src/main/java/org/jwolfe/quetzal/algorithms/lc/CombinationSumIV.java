package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class CombinationSumIV {
    class Solution {
        public int combinationSum4(int[] nums, int target) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;

            int[][] memo = new int[n][target + 1];
            for(int[] row : memo) {
                Arrays.fill(row, -1);
            }

            return combinationSum4(nums, target, 0, memo);
        }

        private int combinationSum4(int[] nums, int target, int index, int[][] memo) {
            if(target < 0) {
                return 0;
            }

            if(target == 0) {
                return 1;
            }

            if(memo[index][target] != -1) {
                return memo[index][target];
            }


            int count = 0;
            for(int i = index; i < nums.length; i++) {
                count += combinationSum4(nums, target - nums[i], index, memo);
            }

            memo[index][target] = count;
            return count;
        }
    }

    class Solution_Recursive {
        public int combinationSum4(int[] nums, int target) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            return combinationSum4(nums, target, 0);
        }

        private int combinationSum4(int[] nums, int target, int index) {
            if(target < 0) {
                return 0;
            }

            if(target == 0) {
                return 1;
            }

            int count = 0;
            for(int i = index; i < nums.length; i++) {
                count += combinationSum4(nums, target - nums[i], index);
            }

            return count;
        }
    }

// [1,2,3]
// 4

// [2,1,3]
// 35
}

//    377. Combination Sum IV
//    Medium
//
//    2140
//
//    244
//
//    Add to List
//
//    Share
//    Given an array of distinct integers nums and a target integer target, return the number of possible combinations that add up to target.
//
//    The answer is guaranteed to fit in a 32-bit integer.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,3], target = 4
//    Output: 7
//    Explanation:
//    The possible combination ways are:
//    (1, 1, 1, 1)
//    (1, 1, 2)
//    (1, 2, 1)
//    (1, 3)
//    (2, 1, 1)
//    (2, 2)
//    (3, 1)
//    Note that different sequences are counted as different combinations.
//    Example 2:
//
//    Input: nums = [9], target = 3
//    Output: 0
//
//
//    Constraints:
//
//    1 <= nums.length <= 200
//    1 <= nums[i] <= 1000
//    All the elements of nums are unique.
//    1 <= target <= 1000
//
//
//    Follow up: What if negative numbers are allowed in the given array? How does it change the problem? What limitation we need to add to the question to allow negative numbers?
