package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class LargestPositiveIntegerThatExistsWithItsNegative {
    class Solution {
        public int findMaxK(int[] nums) {
            if(nums == null || nums.length < 2) {
                return -1;
            }

            int maxVal = -1;

            Set<Integer> set = new HashSet<>();
            for(int val : nums) {
                int inv = (-1) * val;
                if(set.contains(inv)) {
                    maxVal = Math.max(maxVal, Math.abs(val));
                }

                set.add(val);
            }

            return maxVal;
        }
    }

    class Solution_Correct_1 {
        public int findMaxK(int[] nums) {
            if(nums == null || nums.length < 2) {
                return -1;
            }

            int maxVal = -1;

            Set<Integer> set = new HashSet<>();
            for(int val : nums) {
                int check = (-1) * val;
                if(set.contains(check)) {
                    maxVal = Math.max(maxVal, Math.abs(val));
                }

                set.add(val);
            }

            return maxVal;
        }
    }
}

//    2441. Largest Positive Integer That Exists With Its Negative
//    Easy
//    Given an integer array nums that does not contain any zeros, find the largest positive integer k such that -k also exists in the array.
//
//    Return the positive integer k. If there is no such integer, return -1.
//
//
//
//    Example 1:
//
//    Input: nums = [-1,2,-3,3]
//    Output: 3
//    Explanation: 3 is the only valid k we can find in the array.
//    Example 2:
//
//    Input: nums = [-1,10,6,7,-7,1]
//    Output: 7
//    Explanation: Both 1 and 7 have their corresponding negative values in the array. 7 has a larger value.
//    Example 3:
//
//    Input: nums = [-10,8,6,7,-2,-3]
//    Output: -1
//    Explanation: There is no a single valid k, we return -1.
//
//
//    Constraints:
//
//    1 <= nums.length <= 1000
//    -1000 <= nums[i] <= 1000
//    nums[i] != 0