package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class MinimizeMaximumPairSumInArray {
    class Solution {
        public int minPairSum(int[] nums) {
            if(nums == null || nums.length == 0 || nums.length % 2 != 0) {
                return 0;
            }

            int minMaxPairSum = Integer.MIN_VALUE;

            Arrays.sort(nums);
            int n = nums.length;
            for(int i = 0; i < n / 2; i++) {
                int j = n - i - 1;
                int sum = nums[i] + nums[j];

                minMaxPairSum = Math.max(minMaxPairSum, sum);
            }

            return minMaxPairSum;
        }
    }

    class Solution_Correct_1 {
        public int minPairSum(int[] nums) {
            if(nums == null || nums.length == 0 || nums.length % 2 != 0) {
                return 0;
            }

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for(int val : nums) {
                min = Math.min(min, val);
                max = Math.max(max, val);
            }

            return min + max;
        }
    }

// [3,5,2,3]

// [3,5,4,2,4,6]

// [4,1,5,1,2,5,1,5,5,4]

}

//    1877. Minimize Maximum Pair Sum in Array
//    Medium
//    The pair sum of a pair (a,b) is equal to a + b. The maximum pair sum is the largest pair sum in a list of pairs.
//
//    For example, if we have pairs (1,5), (2,3), and (4,4), the maximum pair sum would be max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8.
//    Given an array nums of even length n, pair up the elements of nums into n / 2 pairs such that:
//
//    Each element of nums is in exactly one pair, and
//    The maximum pair sum is minimized.
//    Return the minimized maximum pair sum after optimally pairing up the elements.
//
//
//
//    Example 1:
//
//    Input: nums = [3,5,2,3]
//    Output: 7
//    Explanation: The elements can be paired up into pairs (3,3) and (5,2).
//    The maximum pair sum is max(3+3, 5+2) = max(6, 7) = 7.
//    Example 2:
//
//    Input: nums = [3,5,4,2,4,6]
//    Output: 8
//    Explanation: The elements can be paired up into pairs (3,5), (4,4), and (6,2).
//    The maximum pair sum is max(3+5, 4+4, 6+2) = max(8, 8, 8) = 8.
//
//
//    Constraints:
//
//    n == nums.length
//    2 <= n <= 105
//    n is even.
//    1 <= nums[i] <= 105