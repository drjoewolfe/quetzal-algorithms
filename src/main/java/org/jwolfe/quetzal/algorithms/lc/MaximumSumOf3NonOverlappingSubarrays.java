package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximumSumOf3NonOverlappingSubarrays {
    class Solution {
        public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
            if (nums == null || nums.length == 0 || k < 1) {
                return new int[0];
            }

            int n = nums.length - k + 1;
            int[] sums = computeSums(nums, n, k);

            int[][] memo = new int[n][4];
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }

            computeOptimalSums(sums, k, 0, 3, memo);

            List<Integer> indices = new ArrayList<>();
            constructIndices(sums, k, 0, 3, memo, indices);

            int[] results = new int[3];
            System.out.println(indices.size());
            for (int i = 0; i < 3; i++) {
                results[i] = indices.get(i);
            }

            return results;
        }

        private int[] computeSums(int[] nums, int n, int k) {
            int[] sums = new int[n];
            int windowSum = 0;
            for (int i = 0; i < k; i++) {
                windowSum += nums[i];
            }

            sums[0] = windowSum;
            for (int i = k; i < nums.length; i++) {
                windowSum = windowSum - nums[i - k] + nums[i];
                sums[i - k + 1] = windowSum;
            }

            return sums;
        }

        private int computeOptimalSums(int[] sums, int k, int index, int rem, int[][] memo) {
            if (rem == 0) {
                return 0;
            }

            if (index >= sums.length) {
                return (rem > 0) ? Integer.MIN_VALUE : 0;
            }

            if (memo[index][rem] != -1) {
                return memo[index][rem];
            }

            int take = sums[index] + computeOptimalSums(sums, k, index + k, rem - 1, memo);
            int skip = computeOptimalSums(sums, k, index + 1, rem, memo);

            return memo[index][rem] = Math.max(take, skip);
        }

        private void constructIndices(int[] sums, int k, int index, int rem, int[][] memo, List<Integer> indices) {
            if (rem == 0) {
                return;
            }

            if (index >= sums.length) {
                return;
            }

            int take = sums[index] + computeOptimalSums(sums, k, index + k, rem - 1, memo);
            int skip = computeOptimalSums(sums, k, index + 1, rem, memo);

            if (take >= skip) {
                indices.add(index);
                constructIndices(sums, k, index + k, rem - 1, memo, indices);
            } else {
                constructIndices(sums, k, index + 1, rem, memo, indices);
            }
        }

        private void print(int[] arr) {
            for (int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }

        private void print(List<Integer> arr) {
            for (int a : arr) {
                System.out.print(a + " ");
            }

            System.out.println();
        }
    }
}

//    689. Maximum Sum of 3 Non-Overlapping Subarrays
//    Hard
//    Given an integer array nums and an integer k, find three non-overlapping subarrays of length k with maximum sum and return them.
//
//    Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.
//
//
//
//    Example 1:
//
//    Input: nums = [1,2,1,2,6,7,5,1], k = 2
//    Output: [0,3,5]
//    Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
//    We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
//    Example 2:
//
//    Input: nums = [1,2,1,2,1,2,1,2,1], k = 2
//    Output: [0,2,4]
//
//
//    Constraints:
//
//    1 <= nums.length <= 2 * 104
//    1 <= nums[i] < 216
//    1 <= k <= floor(nums.length / 3)