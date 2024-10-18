package org.jwolfe.quetzal.algorithms.lc;

public class CountNumberOfMaximumBitwiseORSubsets {
    class Solution {
        public int countMaxOrSubsets(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int max_xor = 0;
            for (int val : nums) {
                max_xor |= val;
            }

            int n = nums.length;
            Integer[][] memo = new Integer[n][max_xor + 1];

            return countMaxOrSubsets(nums, 0, 0, max_xor, memo);
        }

        private int countMaxOrSubsets(int[] nums, int index, int curr_xor, int max_xor, Integer[][] memo) {
            if (index == nums.length) {
                return (curr_xor == max_xor) ? 1 : 0;
            }

            if (memo[index][curr_xor] != null) {
                return memo[index][curr_xor];
            }

            int with = countMaxOrSubsets(nums, index + 1, curr_xor | nums[index], max_xor, memo);
            int without = countMaxOrSubsets(nums, index + 1, curr_xor, max_xor, memo);

            return memo[index][curr_xor] = with + without;
        }
    }

    class Solution_Recursive {
        public int countMaxOrSubsets(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int max_xor = 0;
            for (int val : nums) {
                max_xor |= val;
            }

            return countMaxOrSubsets(nums, 0, 0, max_xor);
        }

        private int countMaxOrSubsets(int[] nums, int index, int curr_xor, int max_xor) {
            if (index == nums.length) {
                return (curr_xor == max_xor) ? 1 : 0;
            }

            int with = countMaxOrSubsets(nums, index + 1, curr_xor | nums[index], max_xor);
            int without = countMaxOrSubsets(nums, index + 1, curr_xor, max_xor);

            return with + without;
        }
    }
}

//    2044. Count Number of Maximum Bitwise-OR Subsets
//    Medium
//    Given an integer array nums, find the maximum possible bitwise OR of a subset of nums and return the number of different non-empty subsets with the maximum bitwise OR.
//
//    An array a is a subset of an array b if a can be obtained from b by deleting some (possibly zero) elements of b. Two subsets are considered different if the indices of the elements chosen are different.
//
//    The bitwise OR of an array a is equal to a[0] OR a[1] OR ... OR a[a.length - 1] (0-indexed).
//
//
//
//    Example 1:
//
//    Input: nums = [3,1]
//    Output: 2
//    Explanation: The maximum possible bitwise OR of a subset is 3. There are 2 subsets with a bitwise OR of 3:
//    - [3]
//    - [3,1]
//    Example 2:
//
//    Input: nums = [2,2,2]
//    Output: 7
//    Explanation: All non-empty subsets of [2,2,2] have a bitwise OR of 2. There are 23 - 1 = 7 total subsets.
//    Example 3:
//
//    Input: nums = [3,2,1,5]
//    Output: 6
//    Explanation: The maximum possible bitwise OR of a subset is 7. There are 6 subsets with a bitwise OR of 7:
//    - [3,5]
//    - [3,1,5]
//    - [3,2,5]
//    - [3,2,1,5]
//    - [2,5]
//    - [2,1,5]
//
//
//    Constraints:
//
//    1 <= nums.length <= 16
//    1 <= nums[i] <= 105