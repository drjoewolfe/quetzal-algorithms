package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class SmallestMissingIntegerGreaterThanSequentialPrefixSum {
    class Solution {
        public int missingInteger(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int lps = nums[0];
            int sum = nums[0];

            for(int i = 1; i < n; i++) {
                if(nums[i] == nums[i - 1] + 1) {
                    sum += nums[i];
                } else {
                    lps = Math.max(lps, sum);
                    break;
                }
            }

            Set<Integer> set = new HashSet<>();
            for(int a : nums) {
                set.add(a);
            }

            lps = Math.max(lps, sum);

            int result = lps;
            while(set.contains(result)) {
                result++;
            }

            return result;
        }
    }

// [1,2,3,2,5]
// [3,4,5,1,12,14,13]
// [14,9,6,9,7,9,10,4,9,9,4,4]
}

//    2996. Smallest Missing Integer Greater Than Sequential Prefix Sum
//    Easy
//    You are given a 0-indexed array of integers nums.
//
//    A prefix nums[0..i] is sequential if, for all 1 <= j <= i, nums[j] = nums[j - 1] + 1. In particular, the prefix consisting only of nums[0] is sequential.
//
//    Return the smallest integer x missing from nums such that x is greater than or equal to the sum of the longest sequential prefix.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,3,2,5]
//    Output: 6
//    Explanation: The longest sequential prefix of nums is [1,2,3] with a sum of 6. 6 is not in the array, therefore 6 is the smallest missing integer greater than or equal to the sum of the longest sequential prefix.
//    Example 2:
//
//    Input: nums = [3,4,5,1,12,14,13]
//    Output: 15
//    Explanation: The longest sequential prefix of nums is [3,4,5] with a sum of 12. 12, 13, and 14 belong to the array while 15 does not. Therefore 15 is the smallest missing integer greater than or equal to the sum of the longest sequential prefix.
//
//
//    Constraints:
//
//    1 <= nums.length <= 50
//    1 <= nums[i] <= 50