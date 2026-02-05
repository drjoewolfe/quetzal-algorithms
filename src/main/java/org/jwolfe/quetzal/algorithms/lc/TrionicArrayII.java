package org.jwolfe.quetzal.algorithms.lc;

public class TrionicArrayII {
    class Solution {
        static final long NEG_INF = -1_000_000_000_000_000L;

        public long maxSumTrionic(int[] nums) {
            if (nums == null || nums.length < 3) {
                return 0L;
            }

            Long[][] memo = new Long[nums.length][4];
            return maxSumTrionic(nums, 0, 0, memo);
        }

        private long maxSumTrionic(int[] nums, int index, int trend, Long[][] memo) {
            if (index == nums.length) {
                if (trend == 3) {
                    return 0;
                } else {
                    return NEG_INF;
                }
            }

            if (memo[index][trend] != null) {
                return memo[index][trend];
            }

            long take = NEG_INF;
            long skip = NEG_INF;

            if (trend == 0) {
                skip = maxSumTrionic(nums, index + 1, 0, memo);
            }

            if (trend == 3) {
                take = nums[index];
            }

            if (index + 1 < nums.length) {
                int curr = nums[index];
                int next = nums[index + 1];

                if (trend == 0 && curr < next) {
                    take = Math.max(take, curr + maxSumTrionic(nums, index + 1, 1, memo));
                } else if (trend == 1) {
                    if (curr < next) {
                        take = Math.max(take, curr + maxSumTrionic(nums, index + 1, 1, memo));
                    } else if (curr > next) {
                        take = Math.max(take, curr + maxSumTrionic(nums, index + 1, 2, memo));
                    }
                } else if (trend == 2) {
                    if (curr < next) {
                        take = Math.max(take, curr + maxSumTrionic(nums, index + 1, 3, memo));
                    } else if (curr > next) {
                        take = Math.max(take, curr + maxSumTrionic(nums, index + 1, 2, memo));
                    }
                } else if (trend == 3 && curr < next) {
                    take = Math.max(take, curr + maxSumTrionic(nums, index + 1, 3, memo));
                }
            }

            return memo[index][trend] = Math.max(take, skip);
        }
    }
}

//    3640. Trionic Array II
//    Hard
//    You are given an integer array nums of length n.
//
//    A trionic subarray is a contiguous subarray nums[l...r] (with 0 <= l < r < n) for which there exist indices l < p < q < r such that:
//
//    nums[l...p] is strictly increasing,
//    nums[p...q] is strictly decreasing,
//    nums[q...r] is strictly increasing.
//    Return the maximum sum of any trionic subarray in nums.
//
//
//
//    Example 1:
//
//    Input: nums = [0,-2,-1,-3,0,2,-1]
//
//    Output: -4
//
//    Explanation:
//
//    Pick l = 1, p = 2, q = 3, r = 5:
//
//    nums[l...p] = nums[1...2] = [-2, -1] is strictly increasing (-2 < -1).
//    nums[p...q] = nums[2...3] = [-1, -3] is strictly decreasing (-1 > -3)
//    nums[q...r] = nums[3...5] = [-3, 0, 2] is strictly increasing (-3 < 0 < 2).
//    Sum = (-2) + (-1) + (-3) + 0 + 2 = -4.
//    Example 2:
//
//    Input: nums = [1,4,2,7]
//
//    Output: 14
//
//    Explanation:
//
//    Pick l = 0, p = 1, q = 2, r = 3:
//
//    nums[l...p] = nums[0...1] = [1, 4] is strictly increasing (1 < 4).
//    nums[p...q] = nums[1...2] = [4, 2] is strictly decreasing (4 > 2).
//    nums[q...r] = nums[2...3] = [2, 7] is strictly increasing (2 < 7).
//    Sum = 1 + 4 + 2 + 7 = 14.
//
//
//    Constraints:
//
//    4 <= n = nums.length <= 105
//    -109 <= nums[i] <= 109
//    It is guaranteed that at least one trionic subarray exists.