package org.jwolfe.quetzal.algorithms.lc;

public class LongestSubarrayWithMaximumBitwiseAND {
    class Solution {
        public int longestSubarray(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int max = 0;
            int maxLength = 0;
            int streak = 0;

            for (int val : nums) {
                if (val > max) {
                    max = val;
                    streak = 1;
                    maxLength = 1;
                } else if (val == max) {
                    streak++;
                } else {
                    streak = 0;
                }

                maxLength = Math.max(maxLength, streak);
            }

            return maxLength;
        }
    }

    class Solution_Correct_1 {
        public int longestSubarray(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int maxVal = 0;
            for (int val : nums) {
                maxVal = Math.max(maxVal, val);
            }

            int maxLength = 0;
            int length = 0;
            for (int val : nums) {
                if (val == maxVal) {
                    length++;
                    maxLength = Math.max(maxLength, length);
                } else {
                    length = 0;
                }
            }

            return maxLength;
        }
    }

    class Solution_Brute_TLE {
        public int longestSubarray(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int maxBand = 0;
            for (int i = 0; i < n; i++) {
                int band = nums[i];
                for (int j = i; j < n; j++) {
                    band &= nums[j];
                    maxBand = Math.max(maxBand, band);
                }
            }

            int maxLength = 1;
            for (int i = 0; i < n; i++) {
                int band = nums[i];
                for (int j = i; j < n; j++) {
                    band &= nums[j];
                    if (band == maxBand) {
                        maxLength = Math.max(maxLength, j - i + 1);
                    }
                }
            }

            return maxLength;
        }
    }

// [1,2,3,3,2,2]
// [96317,96317,96317,96317,96317,96317,96317,96317,96317,279979]
}

//    2419. Longest Subarray With Maximum Bitwise AND
//    Medium
//    You are given an integer array nums of size n.
//
//    Consider a non-empty subarray from nums that has the maximum possible bitwise AND.
//
//    In other words, let k be the maximum value of the bitwise AND of any subarray of nums. Then, only subarrays with a bitwise AND equal to k should be considered.
//    Return the length of the longest such subarray.
//
//    The bitwise AND of an array is the bitwise AND of all the numbers in it.
//
//    A subarray is a contiguous sequence of elements within an array.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,3,3,2,2]
//    Output: 2
//    Explanation:
//    The maximum possible bitwise AND of a subarray is 3.
//    The longest subarray with that value is [3,3], so we return 2.
//    Example 2:
//
//    Input: nums = [1,2,3,4]
//    Output: 1
//    Explanation:
//    The maximum possible bitwise AND of a subarray is 4.
//    The longest subarray with that value is [4], so we return 1.
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    1 <= nums[i] <= 106