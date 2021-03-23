package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class WiggleSubsequence {
    class Solution {
        public int wiggleMaxLength(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            if(n == 1) {
                return 1;
            }

            int[][] lws = new int[n][2];
            for(int[] e : lws) {
                Arrays.fill(e, 1);
            }

            int maxLength = 1;
            for(int i = 1; i < n; i++) {
                for(int j = 0; j < i; j++) {
                    if(nums[i] > nums[j]) {
                        if(lws[j][1] + 1 > lws[i][0]) {
                            lws[i][0] = lws[j][1] + 1;
                            maxLength = Math.max(maxLength, lws[i][0]);
                        }

                    } else if(nums[i] < nums[j]) {
                        if(lws[j][0] + 1 > lws[i][1]) {
                            lws[i][1] = lws[j][0] + 1;
                            maxLength = Math.max(maxLength, lws[i][1]);
                        }
                    }
                }
            }

            return maxLength;
        }
    }
}

//    376. Wiggle Subsequence
//    Medium
//    Given an integer array nums, return the length of the longest wiggle sequence.
//
//    A wiggle sequence is a sequence where the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with fewer than two elements is trivially a wiggle sequence.
//
//    For example, [1, 7, 4, 9, 2, 5] is a wiggle sequence because the differences (6, -3, 5, -7, 3) are alternately positive and negative.
//    In contrast, [1, 4, 7, 2, 5] and [1, 7, 4, 5, 5] are not wiggle sequences, the first because its first two differences are positive and the second because its last difference is zero.
//    A subsequence is obtained by deleting some elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original order.
//
//
//
//    Example 1:
//
//    Input: nums = [1,7,4,9,2,5]
//    Output: 6
//    Explanation: The entire sequence is a wiggle sequence.
//    Example 2:
//
//    Input: nums = [1,17,5,10,13,15,10,5,16,8]
//    Output: 7
//    Explanation: There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
//    Example 3:
//
//    Input: nums = [1,2,3,4,5,6,7,8,9]
//    Output: 2
