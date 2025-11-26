package org.jwolfe.quetzal.algorithms.lc;

public class PathsInMatrixWhoseSumIsDivisibleByK {
    class Solution {
        private int MOD = 1_000_000_007;

        public int numberOfPaths(int[][] grid, int k) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            long[][][] dp = new long[m + 1][n + 1][k];

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == 1 && j == 1) {
                        dp[i][j][grid[0][0] % k] = 1;
                        continue;
                    }

                    int value = grid[i - 1][j - 1] % k;
                    for (int r = 0; r < k; r++) {
                        int prevMod = (r - value + k) % k;
                        dp[i][j][r] = (dp[i - 1][j][prevMod] + dp[i][j - 1][prevMod]) % MOD;
                    }
                }
            }

            return (int) dp[m][n][0];
        }
    }
}

//    2435. Paths in Matrix Whose Sum Is Divisible by K
//    Hard
//    You are given a 0-indexed m x n integer matrix grid and an integer k. You are currently at position (0, 0) and you want to reach position (m - 1, n - 1) moving only down or right.
//
//    Return the number of paths where the sum of the elements on the path is divisible by k. Since the answer may be very large, return it modulo 109 + 7.
//
//
//
//    Example 1:
//
//
//    Input: grid = [[5,2,4],[3,0,5],[0,7,2]], k = 3
//    Output: 2
//    Explanation: There are two paths where the sum of the elements on the path is divisible by k.
//    The first path highlighted in red has a sum of 5 + 2 + 4 + 5 + 2 = 18 which is divisible by 3.
//    The second path highlighted in blue has a sum of 5 + 3 + 0 + 5 + 2 = 15 which is divisible by 3.
//    Example 2:
//
//
//    Input: grid = [[0,0]], k = 5
//    Output: 1
//    Explanation: The path highlighted in red has a sum of 0 + 0 = 0 which is divisible by 5.
//    Example 3:
//
//
//    Input: grid = [[7,3,4,9],[2,3,6,2],[2,3,7,0]], k = 1
//    Output: 10
//    Explanation: Every integer is divisible by 1 so the sum of the elements on every possible path is divisible by k.
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    1 <= m, n <= 5 * 104
//    1 <= m * n <= 5 * 104
//    0 <= grid[i][j] <= 100
//    1 <= k <= 50