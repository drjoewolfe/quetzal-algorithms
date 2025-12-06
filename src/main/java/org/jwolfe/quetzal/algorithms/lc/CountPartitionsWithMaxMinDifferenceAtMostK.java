package org.jwolfe.quetzal.algorithms.lc;

import java.util.TreeMap;

public class CountPartitionsWithMaxMinDifferenceAtMostK {
    class Solution {
        private int MOD = 1_000_000_007;

        public int countPartitions(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int n = nums.length;
            long[] dp = new long[n + 1];
            long[] prefix = new long[n + 1];

            dp[0] = 1;
            prefix[0] = 1;

            TreeMap<Integer, Integer> count = new TreeMap<>();
            for (int i = 0, j = 0; i < n; i++) {
                int x = nums[i];
                count.put(x, count.getOrDefault(x, 0) + 1);

                // Adjust Window
                while (j <= i && count.lastKey() - count.firstKey() > k) {
                    int lx = nums[j];
                    if (count.get(lx) == 1) {
                        count.remove(lx);
                    } else {
                        count.put(lx, count.get(lx) - 1);
                    }

                    j++;
                }

                dp[i + 1] = (prefix[i] - (j > 0 ? prefix[j - 1] : 0) + MOD) % MOD;
                prefix[i + 1] = (prefix[i] + dp[i + 1]) % MOD;
            }

            return (int) dp[n];
        }
    }
}

//    3578. Count Partitions With Max-Min Difference at Most K
//    Medium
//    You are given an integer array nums and an integer k. Your task is to partition nums into one or more non-empty contiguous segments such that in each segment, the difference between its maximum and minimum elements is at most k.
//
//    Return the total number of ways to partition nums under this condition.
//
//    Since the answer may be too large, return it modulo 109 + 7.
//
//
//
//    Example 1:
//
//    Input: nums = [9,4,1,3,7], k = 4
//
//    Output: 6
//
//    Explanation:
//
//    There are 6 valid partitions where the difference between the maximum and minimum elements in each segment is at most k = 4:
//
//    [[9], [4], [1], [3], [7]]
//    [[9], [4], [1], [3, 7]]
//    [[9], [4], [1, 3], [7]]
//    [[9], [4, 1], [3], [7]]
//    [[9], [4, 1], [3, 7]]
//    [[9], [4, 1, 3], [7]]
//    Example 2:
//
//    Input: nums = [3,3,4], k = 0
//
//    Output: 2
//
//    Explanation:
//
//    There are 2 valid partitions that satisfy the given conditions:
//
//    [[3], [3], [4]]
//    [[3, 3], [4]]
//
//
//    Constraints:
//
//    2 <= nums.length <= 5 * 104
//    1 <= nums[i] <= 109
//    0 <= k <= 109