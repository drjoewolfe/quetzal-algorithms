package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class MinimumMovesToEqualArrayElementsII {
    class Solution {
        public int minMoves2(int[] nums) {
            if(nums == null || nums.length < 2) {
                return 0;
            }

            int n = nums.length;
            Arrays.sort(nums);

            int median = nums[n / 2];
            int moves = 0;
            for(int a : nums) {
                moves += Math.abs(median - a);
            }

            return moves;
        }
    }

    class Solution_Incorrect {
        public int minMoves2(int[] nums) {
            if(nums == null || nums.length < 2) {
                return 0;
            }

            int n = nums.length;
            int sum = 0;
            for(int a : nums) {
                sum += a;
            }

            int avg = sum / n;
            int moves = 0;
            for(int a : nums) {
                moves += Math.abs(avg - a);
            }

            System.out.println(sum + ", " + avg + ", " + moves);
            return moves;
        }
    }

// [1,2,3]
}

//    462. Minimum Moves to Equal Array Elements II
//    Medium
//    Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.
//
//    In one move, you can increment or decrement an element of the array by 1.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,3]
//    Output: 2
//    Explanation:
//    Only two moves are needed (remember each move increments or decrements one element):
//    [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
//    Example 2:
//
//    Input: nums = [1,10,2,9]
//    Output: 16
//
//
//    Constraints:
//
//    n == nums.length
//    1 <= nums.length <= 105
//    -109 <= nums[i] <= 109