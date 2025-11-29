package org.jwolfe.quetzal.algorithms.lc;

public class FindMinimumOperationsToMakeAllElementsDivisibleByThree {
    class Solution {
        public int minimumOperations(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int operations = 0;
            for (int x : nums) {
                x = x % 3;
                if (x != 0) {
                    operations += Math.min(x, (3 - x));
                }
            }

            return operations;
        }
    }

    class Solution_Correct_1 {
        public int minimumOperations(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int operations = 0;
            for (int x : nums) {
                x = x % 3;
                if (x != 0) {
                    operations += Math.min(x, 3 - x);
                }
            }

            return operations;
        }
    }
}

//    3190. Find Minimum Operations to Make All Elements Divisible by Three
//    Easy
//    You are given an integer array nums. In one operation, you can add or subtract 1 from any element of nums.
//
//    Return the minimum number of operations to make all elements of nums divisible by 3.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,3,4]
//
//    Output: 3
//
//    Explanation:
//
//    All array elements can be made divisible by 3 using 3 operations:
//
//    Subtract 1 from 1.
//    Add 1 to 2.
//    Subtract 1 from 4.
//    Example 2:
//
//    Input: nums = [3,6,9]
//
//    Output: 0
//
//
//
//    Constraints:
//
//    1 <= nums.length <= 50
//    1 <= nums[i] <= 50