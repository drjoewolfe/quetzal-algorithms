package org.jwolfe.quetzal.algorithms.lc;

public class ShortestSubarrayWithORAtLeastKII {
    class Solution {
        public int minimumSubarrayLength(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k < 0) {
                return -1;
            }

            int n = nums.length;
            int left = 0;
            int right = 0;

            int minLength = Integer.MAX_VALUE;
            int[] bitCounts = new int[32];

            while (right < n) {
                int val = nums[right];
                updateBitCounts(bitCounts, val, 1);

                while (left <= right
                        && getNumber(bitCounts) >= k) {
                    int size = right - left + 1;
                    minLength = Math.min(minLength, size);

                    int leftVal = nums[left];
                    updateBitCounts(bitCounts, leftVal, -1);
                    left++;
                }

                right++;
            }

            return (minLength == Integer.MAX_VALUE) ? -1 : minLength;
        }

        private void updateBitCounts(int[] bitCounts, int number, int delta) {
            for (int pos = 0; pos < 32; pos++) {
                if (((number >> pos) & 1) != 0) {
                    bitCounts[pos] += delta;
                }
            }
        }

        private int getNumber(int[] bitCounts) {
            int number = 0;
            for (int pos = 0; pos < 32; pos++) {
                if (bitCounts[pos] != 0) {
                    number |= (1 << pos);
                }
            }

            return number;
        }
    }
}

//    3097. Shortest Subarray With OR at Least K II
//    Medium
//    You are given an array nums of non-negative integers and an integer k.
//
//    An array is called special if the bitwise OR of all of its elements is at least k.
//
//    Return the length of the shortest special non-empty subarray of nums, or return -1 if no special subarray exists.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,3], k = 2
//
//    Output: 1
//
//    Explanation:
//
//    The subarray [3] has OR value of 3. Hence, we return 1.
//
//    Example 2:
//
//    Input: nums = [2,1,8], k = 10
//
//    Output: 3
//
//    Explanation:
//
//    The subarray [2,1,8] has OR value of 11. Hence, we return 3.
//
//    Example 3:
//
//    Input: nums = [1,2], k = 0
//
//    Output: 1
//
//    Explanation:
//
//    The subarray [1] has OR value of 1. Hence, we return 1.
//
//
//
//    Constraints:
//
//    1 <= nums.length <= 2 * 105
//    0 <= nums[i] <= 109
//    0 <= k <= 109