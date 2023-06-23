package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashMap;
import java.util.Map;

public class LongestArithmeticSubsequence {
    class Solution {
        public int longestArithSeqLength(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int maxLength = 1;
            int n = nums.length;

            Map<Integer, Integer>[] dp = new HashMap[n];

            for(int j = 0; j < n; j++) {
                int b = nums[j];

                HashMap<Integer, Integer> map = new HashMap<>();
                dp[j] = map;

                for(int i = 0; i < j; i++) {
                    int a = nums[i];
                    int d = b - a;

                    map.put(d, dp[i].getOrDefault(d, 1) + 1);
                    maxLength = Math.max(maxLength, map.get(d));
                }

            }

            return maxLength;
        }
    }

    class Solution_TLE {
        public int longestArithSeqLength(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int maxLength = 1;
            int n = nums.length;

            for(int i = 0; i < n - 1; i++) {
                int a = nums[i];
                for(int j = i + 1; j < n; j++) {
                    int b = nums[j];
                    int d = b - a;

                    maxLength = Math.max(maxLength,
                            longestArithSeqLength(nums, j + 1, b, d, 2));
                }
            }

            return maxLength;
        }

        private int longestArithSeqLength(int[] nums, int index, int prev, int d, int currentLength) {
            if(index == nums.length) {
                return currentLength;
            }

            int curr = nums[index];
            if(curr - prev == d) {
                return longestArithSeqLength(nums, index + 1, curr, d, currentLength + 1);
            } else {
                return longestArithSeqLength(nums, index + 1, prev, d, currentLength);
            }
        }
    }

// [3,6,9,12]
// [0,1,0,1,0,1,0,0,1,0,0,1,0,1,1,1,1,0,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,0,0,1,1,1,0,0,1,0,1,0,0,1,0,0,1,1,0,1,1,1,1,0,0,1,1,0,0,1,0,0,0,0,1,1,0,0,0,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,1,0,1,0,0,1,0,1,1,0,0,1,1,1,1,1,1,1,1,0,1,1,0,0,1,0,1,1,1,1,0,1,1,0,0,1,1,1,0,1,0,0,0,1,0,1,1,0,0,1,0,1,1,1,0,0,1,0,0,1,0,0,1,0,1,0,0,0,1,0,1,1,0,0,0,0,1,0,1,1,1,1,1,0,0,0,1,1,0,0,1,1,0,0,0,1,1,0,0,0,0,0,1,0,0,0,0,0,0,1,1,1,1,1,0,0,0,1,1,1,1,1,0,0,1,1,1,1,0,1,0,1,0,0,1,0,0,1,1,1,1,0,0,0,0,0,1,0,1,0,0,0,0,1,0,1,1,1,1,1,1,0,0,1,0,1,1,1,0,0,0,0,1,0,1,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,0,0,0,0,1,1,1,0,0,1,0,1,0,1,0,1,0,0,0,1,0,0,0,0,0,1,1,1,1,1,1,1,0,0,1,0,1,1,0,0,1,1,1,1,0,1,0,1,0,1,1,0,1,1,0,1,0,0,1,0,1,0,0,0,1,0,0,0,0,1,0,0,1,0,1,0,1,0,1,1,1,0,0,1,1,0,0,1,0,0,1,0,0,0,1,1,1,1,1,0,0,1,0,1,1,1,1,0,1,1,0,1,0,0,1,1,1,1,0,0,0,1,1,1,0,0,1,0,0,0,0,1,1,0,1,1,1,0,0,0,0,0,0,1,0,0,0,0,0,1,1,0,0,1,1,0,1,0,0,0,0,1,0,0,0,1,1,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,1,0,1,0,0,0,1,1,0,1,1,0,0,1,1,0,0,0,1,0,1,1,0,1,1,1,1,0,0,0,1,1,0,1,0,1,1,0,0,1,0,1,0,1,0,0,1,1,1,1,0,0,0,0,0,1,0,0,0,1,0,1,0,1,0,1,1,0,1,0,0,0,0,0,1,1,0,1,0,0,1,1,1,0,0,1,0,1,0,1,0,0,1,1,0,0,1,0,1,1,0,0,0,0,1,0,0,1,1,1,0,0,1,1,0,1,0,0,0,1,1,1,1,1,0,0,0,1,1,1,0,1,0,0,0,1,0,1,0,1,0,1,1,0,0,1,0,0,1,0,0,0,0,0,1,0,1,1,1,0,1,1,0,1,0,1,0,1,1,0,0,0,1,1,1,1,1,0,0,1,0,0,1,0,1,1,1,0,0,1,1,1,1,1,0,1,0,0,1,1,0,1,0,1,0,0,0,1,1,1,0,0,0,0,1,0,0,1,0,0,1,0,0,1,0,1,1,1,1,1,0,1,0,0,1,0,0,1,1,0,0,0,1,0,1,0,0,1,0,0,0,0,1,0,1,1,0,1,1,0,0,1,1,1,0,0,0,1,1,0,0,1,0,1,0,1,0,1,0,0,0,0,1,0,1,1,1,1,1,1,0,1,1,0,0,0,0,0,0,0,0,1,0,0,0,1,1,1,1,1,1,0,0,0,0,1,1,1,0,0,1,0,1,0,0,0,0,1,0,0,0,0,0,0,1,0,1,0,0,0,0,1,1,0,1,1,1,0,0,0,0,0,0,1,1,0,1,0,0,0,1,1,1,0,1,0,1,1,1,1,0,0,0,0,1,1,1,0,1,1,0,0,0,1,0,0,0,1,1,1,0,0,1,0,1,0,1,0,0,1,0,1,1,1,0,1,1,1,0,0,1,0,1,0,1,0,0,1,0,1,1,0,1,1,0,1,1,1,0,0,0,0,1,1,1,0,0,1,0,0,0,0,1,0,1,1,1,0,0,1,0,0,0,1,1,1,0,0,1,0,0,1,1,0,1,0,0,1,0,1,1,1,1,1,1,0,0,1,0,1,1,1,1,0,0,0,0,0,1,1,0,1,1,0,0,1,0,1,0,1,1,0,0,1,1,1,0,0,0,1,1,1,0,1,0,1,1,1,1,0,1,1,0]
}

//    1027. Longest Arithmetic Subsequence
//    Medium
//    Given an array nums of integers, return the length of the longest arithmetic subsequence in nums.
//
//    Note that:
//
//    A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
//    A sequence seq is arithmetic if seq[i + 1] - seq[i] are all the same value (for 0 <= i < seq.length - 1).
//
//
//    Example 1:
//
//    Input: nums = [3,6,9,12]
//    Output: 4
//    Explanation:  The whole array is an arithmetic sequence with steps of length = 3.
//    Example 2:
//
//    Input: nums = [9,4,7,2,10]
//    Output: 3
//    Explanation:  The longest arithmetic subsequence is [4,7,10].
//    Example 3:
//
//    Input: nums = [20,1,15,3,10,5,8]
//    Output: 4
//    Explanation:  The longest arithmetic subsequence is [20,15,10,5].
//
//
//    Constraints:
//
//    2 <= nums.length <= 1000
//    0 <= nums[i] <= 500