package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class MaxPairSumInAnArray {
    class Solution {
        public int maxSum(int[] nums) {
            if(nums == null || nums.length < 2) {
                return -1;
            }

            int n = nums.length;

            int maxSum = -1;
            Map<Integer, Integer> map = new HashMap<>();
            for(int a : nums) {
                int maxDigit = getMaxDigit(a);
                if(map.containsKey(maxDigit)) {
                    int b = map.get(maxDigit);
                    maxSum = Math.max(maxSum, a + b);
                }

                map.put(maxDigit, Math.max(map.getOrDefault(maxDigit, 0), a));
            }

            return maxSum;
        }

        private int getMaxDigit(int a) {
            int maxDigit = 0;

            while(a > 0) {
                int digit = a % 10;
                maxDigit = Math.max(maxDigit, digit);

                a /= 10;
            }

            return maxDigit;
        }
    }

    class Solution_Correct_1 {
        public int maxSum(int[] nums) {
            if(nums == null || nums.length < 2) {
                return -1;
            }

            int n = nums.length;

            int maxSum = -1;
            for(int i = 0; i < n - 1; i++) {
                int a = nums[i];
                int maxA = getMaxDigit(a);

                for(int j = i + 1; j < n; j++) {
                    int b = nums[j];
                    int maxB = getMaxDigit(b);

                    if(maxA != maxB) {
                        continue;
                    }

                    maxSum = Math.max(maxSum, a + b);
                }
            }

            return maxSum;
        }

        private int getMaxDigit(int a) {
            int maxDigit = 0;

            while(a > 0) {
                int digit = a % 10;
                maxDigit = Math.max(maxDigit, digit);

                a /= 10;
            }

            return maxDigit;
        }
    }

// [51,71,17,24,42]
}

//    2815. Max Pair Sum in an Array
//    Easy
//    You are given a 0-indexed integer array nums. You have to find the maximum sum of a pair of numbers from nums such that the maximum digit in both numbers are equal.
//
//    Return the maximum sum or -1 if no such pair exists.
//
//
//
//    Example 1:
//
//    Input: nums = [51,71,17,24,42]
//    Output: 88
//    Explanation:
//    For i = 1 and j = 2, nums[i] and nums[j] have equal maximum digits with a pair sum of 71 + 17 = 88.
//    For i = 3 and j = 4, nums[i] and nums[j] have equal maximum digits with a pair sum of 24 + 42 = 66.
//    It can be shown that there are no other pairs with equal maximum digits, so the answer is 88.
//    Example 2:
//
//    Input: nums = [1,2,3,4]
//    Output: -1
//    Explanation: No pair exists in nums with equal maximum digits.
//
//
//    Constraints:
//
//    2 <= nums.length <= 100
//    1 <= nums[i] <= 104