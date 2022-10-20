package org.jwolfe.quetzal.algorithms.lc;

public class LongestNiceSubarray {
    class Solution {
        public int longestNiceSubarray(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int maxLength = 1;

            int niceMask = 0;
            int i = 0;
            for(int j = 0; j < n; j++) {
                int val = nums[j];
                while((niceMask & val) != 0) {
                    niceMask ^= nums[i];
                    i++;
                }

                niceMask |= val;
                maxLength = Math.max(maxLength, j - i + 1);
            }

            return maxLength;
        }
    }

    class Solution_Correct {
        public int longestNiceSubarray(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            int maxLength = 1;

            for(int i = 0; i < n; i++) {
                int a = nums[i];
                int length = 1;

                for(int j = i + 1; j < n; j++) {
                    int b = nums[j];

                    if((a & b) == 0) {
                        System.out.println(a + ", " + b);
                        length++;
                        maxLength = Math.max(maxLength, length);
                    }
                }
            }

            return maxLength;
        }
    }
}

//    2401. Longest Nice Subarray
//    Medium
//    You are given an array nums consisting of positive integers.
//
//    We call a subarray of nums nice if the bitwise AND of every pair of elements that are in different positions in the subarray is equal to 0.
//
//    Return the length of the longest nice subarray.
//
//    A subarray is a contiguous part of an array.
//
//    Note that subarrays of length 1 are always considered nice.
//
//
//
//    Example 1:
//
//    Input: nums = [1,3,8,48,10]
//    Output: 3
//    Explanation: The longest nice subarray is [3,8,48]. This subarray satisfies the conditions:
//    - 3 AND 8 = 0.
//    - 3 AND 48 = 0.
//    - 8 AND 48 = 0.
//    It can be proven that no longer nice subarray can be obtained, so we return 3.
//    Example 2:
//
//    Input: nums = [3,1,5,11,13]
//    Output: 1
//    Explanation: The length of the longest nice subarray is 1. Any subarray of length 1 can be chosen.
//
//
//    Constraints:
//
//    1 <= nums.length <= 105
//    1 <= nums[i] <= 109