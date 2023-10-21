package org.jwolfe.quetzal.algorithms.lc;

import java.util.PriorityQueue;

public class ConstrainedSubsequenceSum {
    class Solution {
        public int constrainedSubsetSum(int[] nums, int k) {
            if(nums == null || nums.length == 0 || k < 0) {
                return 0;
            }

            int n = nums.length;
            int maxSum = nums[0];

            PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> {
                return b[0] - a[0];
            });
            maxHeap.offer(new int[] {nums[0], 0});

            for(int i = 1; i < n; i++) {
                while(maxHeap.peek()[1] < i - k) {
                    maxHeap.poll();
                }

                int currMax = nums[i] + Math.max(0, maxHeap.peek()[0]);
                maxSum = Math.max(maxSum, currMax);
                maxHeap.offer(new int[] {currMax, i});
            }

            return maxSum;
        }
    }

    class Solution_Correct_1 {
        public int constrainedSubsetSum(int[] nums, int k) {
            if(nums == null || nums.length == 0 || k < 0) {
                return 0;
            }

            int n = nums.length;

            // dp[i] is the answer for the prefix array that ends at & contains nums[i];
            int[] dp = new int[n];
            int maxSum = nums[0];

            for(int i = 0; i < n; i++) {
                dp[i] = nums[i];
                int max = 0;
                for(int j = i - 1; j >= 0 && j >= i - k; j--) {
                    max = Math.max(max, dp[j]);
                }

                dp[i] += max;
                maxSum = Math.max(maxSum, dp[i]);
            }

            return maxSum;
        }
    }

// [10,2,-10,5,20]
// 2
}

//    1425. Constrained Subsequence Sum
//    Hard
//    Given an integer array nums and an integer k, return the maximum sum of a non-empty subsequence of that array such that for every two consecutive integers in the subsequence, nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.
//
//    A subsequence of an array is obtained by deleting some number of elements (can be zero) from the array, leaving the remaining elements in their original order.
//
//
//
//    Example 1:
//
//    Input: nums = [10,2,-10,5,20], k = 2
//    Output: 37
//    Explanation: The subsequence is [10, 2, 5, 20].
//    Example 2:
//
//    Input: nums = [-1,-2,-3], k = 1
//    Output: -1
//    Explanation: The subsequence must be non-empty, so we choose the largest number.
//    Example 3:
//
//    Input: nums = [10,-2,-10,-5,20], k = 2
//    Output: 23
//    Explanation: The subsequence is [10, -2, -5, 20].
//
//
//    Constraints:
//
//    1 <= k <= nums.length <= 105
//    -104 <= nums[i] <= 104