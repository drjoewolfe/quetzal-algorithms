package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class BuildArrayWhereYouCanFindTheMaximumExactlyKComparisons {
    class Solution {
        private int MOD = 1_000_000_007;

        public int numOfArrays(int n, int m, int k) {
            if(n < 1 || k < 1) {
                return 0;
            }

            int[][][] memo = new int[n + 1][m + 1][k + 1];
            for(int[][] tuple : memo) {
                for(int[] record : tuple) {
                    Arrays.fill(record, -1);
                }
            }

            return dp(n, m, 0, 0, k, memo);
        }

        private int dp(int n, int m, int i, int maxSoFar, int remain, int[][][] memo) {
            if(i == n) {
                if(remain == 0) {
                    return 1;
                } else {
                    return 0;
                }
            }

            if(remain < 0) {
                return 0;
            }

            if(memo[i][maxSoFar][remain] != -1) {
                return memo[i][maxSoFar][remain];
            }

            int ways = 0;

            // No new max
            for(int a = 1; a <= maxSoFar; a++) {
                ways += dp(n, m, i + 1, maxSoFar, remain, memo);
                ways %= MOD;
            }

            // Introduce new max
            for(int a = maxSoFar + 1; a <= m; a++) {
                ways += dp(n, m, i + 1, a, remain - 1, memo);
                ways %= MOD;
            }

            memo[i][maxSoFar][remain] = ways;
            return ways;
        }
    }

    class Solution_Recursive_TLE {
        private int MOD = 1_000_000_007;

        public int numOfArrays(int n, int m, int k) {
            if(n < 1 || k < 1) {
                return 0;
            }

            return dp(n, m, 0, 0, k);
        }

        private int dp(int n, int m, int i, int maxSoFar, int remain) {
            if(i == n) {
                if(remain == 0) {
                    return 1;
                } else {
                    return 0;
                }
            }

            int ways = 0;

            // No new max
            ways += (maxSoFar * dp(n, m, i + 1, maxSoFar, remain));

            // Introduce new max
            for(int a = maxSoFar + 1; a <= m; a++) {
                ways += dp(n, m, i + 1, a, remain - 1);
            }

            return ways;
        }
    }

// 2
// 3
// 1

// 50
// 100
// 25
}

//    1420. Build Array Where You Can Find The Maximum Exactly K Comparisons
//    Hard
//    You are given three integers n, m and k. Consider the following algorithm to find the maximum element of an array of positive integers:
//
//
//    You should build the array arr which has the following properties:
//
//    arr has exactly n integers.
//    1 <= arr[i] <= m where (0 <= i < n).
//    After applying the mentioned algorithm to arr, the value search_cost is equal to k.
//    Return the number of ways to build the array arr under the mentioned conditions. As the answer may grow large, the answer must be computed modulo 109 + 7.
//
//
//
//    Example 1:
//
//    Input: n = 2, m = 3, k = 1
//    Output: 6
//    Explanation: The possible arrays are [1, 1], [2, 1], [2, 2], [3, 1], [3, 2] [3, 3]
//    Example 2:
//
//    Input: n = 5, m = 2, k = 3
//    Output: 0
//    Explanation: There are no possible arrays that satisify the mentioned conditions.
//    Example 3:
//
//    Input: n = 9, m = 1, k = 1
//    Output: 1
//    Explanation: The only possible array is [1, 1, 1, 1, 1, 1, 1, 1, 1]
//
//
//    Constraints:
//
//    1 <= n <= 50
//    1 <= m <= 100
//    0 <= k <= n