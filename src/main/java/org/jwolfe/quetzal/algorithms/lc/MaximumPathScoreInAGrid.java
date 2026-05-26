package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class MaximumPathScoreInAGrid {
    class Solution {
        public int maxPathScore(int[][] grid, int k) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return -1;
            }

            int m = grid.length;
            int n = grid[0].length;

            int[][][] dp = new int[m][n][k + 1];
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    Arrays.fill(dp[r][c], Integer.MIN_VALUE);
                }
            }

            dp[0][0][0] = 0;
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    for (int i = 0; i <= k; i++) {
                        if (dp[r][c][i] == Integer.MIN_VALUE) {
                            continue;
                        }

                        if (r < m - 1) {
                            int val = grid[r + 1][c];
                            int cost = (val == 0) ? 0 : 1;
                            if (i + cost <= k) {
                                dp[r + 1][c][i + cost] = Math.max(
                                        dp[r + 1][c][i + cost],
                                        dp[r][c][i] + val
                                );
                            }
                        }

                        if (c < n - 1) {
                            int val = grid[r][c + 1];
                            int cost = (val == 0) ? 0 : 1;
                            if (i + cost <= k) {
                                dp[r][c + 1][i + cost] = Math.max(
                                        dp[r][c + 1][i + cost],
                                        dp[r][c][i] + val
                                );
                            }
                        }
                    }
                }
            }

            int ans = Integer.MIN_VALUE;
            for (int i = 0; i <= k; i++) {
                ans = Math.max(ans, dp[m - 1][n - 1][i]);
            }

            return ans < 0 ? -1 : ans;
        }
    }
}

//    3742. Maximum Path Score in a Grid
//    Medium
//    You are given an m x n grid where each cell contains one of the values 0, 1, or 2. You are also given an integer k.
//
//    You start from the top-left corner (0, 0) and want to reach the bottom-right corner (m - 1, n - 1) by moving only right or down.
//
//    Each cell contributes a specific score and incurs an associated cost, according to their cell values:
//
//    0: adds 0 to your score and costs 0.
//    1: adds 1 to your score and costs 1.
//    2: adds 2 to your score and costs 1. ​​​​​​​
//    Return the maximum score achievable without exceeding a total cost of k, or -1 if no valid path exists.
//
//    Note: If you reach the last cell but the total cost exceeds k, the path is invalid.
//
//
//
//    Example 1:
//
//    Input: grid = [[0, 1],[2, 0]], k = 1
//
//    Output: 2
//
//    Explanation:​​​​​​​
//
//    The optimal path is:
//
//    Cell	grid[i][j]	Score	Total
//    Score	Cost	Total
//    Cost
//    (0, 0)	0	0	0	0	0
//    (1, 0)	2	2	2	1	1
//    (1, 1)	0	0	2	0	1
//    Thus, the maximum possible score is 2.
//
//    Example 2:
//
//    Input: grid = [[0, 1],[1, 2]], k = 1
//
//    Output: -1
//
//    Explanation:
//
//    There is no path that reaches cell (1, 1)​​​​​​​ without exceeding cost k. Thus, the answer is -1.
//
//
//
//    Constraints:
//
//    1 <= m, n <= 200
//    0 <= k <= 103​​​​​​​
//    ​​​​​​​grid[0][0] == 0
//    0 <= grid[i][j] <= 2