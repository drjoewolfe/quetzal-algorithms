package org.jwolfe.quetzal.algorithms.lc;

public class FindTheLargestAlmostMissingInteger {
    class Solution {
        public int largestInteger(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return -1;
            }

            int n = nums.length;
            if (k == n) {
                // return the max element
                int max = -1;
                for (int x : nums) {
                    max = Math.max(max, x);
                }

                return max;
            }

            int[] frequency = new int[51];
            for (int x : nums) {
                frequency[x]++;
            }

            if (k == 1) {
                // return the max element with a frequency of 1
                for (int x = 50; x >= 0; x--) {
                    if (frequency[x] == 1) {
                        return x;
                    }
                }

                return -1;
            }

            // compare the frequencies of the first & last element & process
            int first = nums[0];
            int last = nums[n - 1];

            int result = -1;

            if (frequency[last] == 1) {
                result = last;
            }

            if (frequency[first] == 1) {
                result = Math.max(result, first);
            }

            return result;
        }
    }
}

//    3471. Find the Largest Almost Missing Integer
//    Easy
//    You are given an integer array nums and an integer k.
//
//    An integer x is almost missing from nums if x appears in exactly one subarray of size k within nums.
//
//    Return the largest almost missing integer from nums. If no such integer exists, return -1.
//
//    A subarray is a contiguous sequence of elements within an array.
//
//
//    Example 1:
//
//    Input: nums = [3,9,2,1,7], k = 3
//
//    Output: 7
//
//    Explanation:
//
//    1 appears in 2 subarrays of size 3: [9, 2, 1] and [2, 1, 7].
//    2 appears in 3 subarrays of size 3: [3, 9, 2], [9, 2, 1], [2, 1, 7].
//    3 appears in 1 subarray of size 3: [3, 9, 2].
//    7 appears in 1 subarray of size 3: [2, 1, 7].
//    9 appears in 2 subarrays of size 3: [3, 9, 2], and [9, 2, 1].
//    We return 7 since it is the largest integer that appears in exactly one subarray of size k.
//
//    Example 2:
//
//    Input: nums = [3,9,7,2,1,7], k = 4
//
//    Output: 3
//
//    Explanation:
//
//    1 appears in 2 subarrays of size 4: [9, 7, 2, 1], [7, 2, 1, 7].
//    2 appears in 3 subarrays of size 4: [3, 9, 7, 2], [9, 7, 2, 1], [7, 2, 1, 7].
//    3 appears in 1 subarray of size 4: [3, 9, 7, 2].
//    7 appears in 3 subarrays of size 4: [3, 9, 7, 2], [9, 7, 2, 1], [7, 2, 1, 7].
//    9 appears in 2 subarrays of size 4: [3, 9, 7, 2], [9, 7, 2, 1].
//    We return 3 since it is the largest and only integer that appears in exactly one subarray of size k.
//
//    Example 3:
//
//    Input: nums = [0,0], k = 1
//
//    Output: -1
//
//    Explanation:
//
//    There is no integer that appears in only one subarray of size 1.
//
//
//
//    Constraints:
//
//    1 <= nums.length <= 50
//    0 <= nums[i] <= 50
//    1 <= k <= nums.length