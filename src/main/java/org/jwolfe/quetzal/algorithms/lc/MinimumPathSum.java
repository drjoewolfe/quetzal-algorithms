package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class MinimumPathSum {
    class Solution {
        public int minPathSum(int[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            int[][] dp = new int[m][n];
            dp[0][0] = grid[0][0];

            for(int i = 1; i < m; i++) {
                dp[i][0] = dp[i - 1][0] + grid[i][0];
            }

            for(int j = 1; j < n; j++) {
                dp[0][j] = dp[0][j - 1] + grid[0][j];
            }

            for(int i = 1; i < m;  i++) {
                for(int j = 1; j < n; j++) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }

            return dp[m - 1][n - 1];
        }
    }

    class Solution_Correct_1 {
        public int minPathSum(int[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length == 0) {
                return -1;
            }

            int rows = grid.length;
            int columns = grid[0].length;

            for(int[] row : grid) {
                if(row.length != columns) {
                    return -1;
                }
            }

            int[][] dp = new int[rows][columns];
            dp[0][0] = grid[0][0];

            for(int j = 1; j < columns; j++) {
                dp[0][j] = dp[0][j - 1] + grid[0][j];
            }

            for(int i = 1; i < rows; i++) {
                dp[i][0] = dp[i - 1][0] + grid[i][0];
            }

            for(int i = 1; i < rows; i++) {
                for(int j = 1; j < columns; j++) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }

            return dp[rows - 1][columns - 1];
        }

        public int minPathSum_Memoized(int[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length == 0) {
                return -1;
            }

            int rows = grid.length;
            int columns = grid[0].length;

            for(int[] row : grid) {
                if(row.length != columns) {
                    return -1;
                }
            }

            int[][] memo = new int[rows][columns];
            for(int[] memoRow : memo) {
                Arrays.fill(memoRow, Integer.MAX_VALUE);
            }

            return minPathSum_Memoized(grid, rows - 1, columns - 1, memo);
        }

        private int minPathSum_Memoized(int[][] grid, int x, int y, int[][] memo) {
            if(x < 0 || y < 0) {
                return Integer.MAX_VALUE;
            }

            if(memo[x][y] != Integer.MAX_VALUE) {
                return memo[x][y];
            }

            int pathSum = 0;

            if(x == 0 && y == 0) {
                pathSum = grid[0][0];
            } else {
                int costFromLeft = minPathSum_Memoized(grid, x, y - 1, memo);
                int costFromTop = minPathSum_Memoized(grid, x - 1, y, memo);

                pathSum = Math.min(costFromLeft, costFromTop) + grid[x][y];
            }

            memo[x][y] = pathSum;
            return pathSum;
        }

        private int minPathSum(int[][] grid, int x, int y) {
            if(x < 0 || y < 0) {
                return Integer.MAX_VALUE;
            }

            if(x == 0 && y == 0) {
                return grid[0][0];
            }

            int costFromLeft = minPathSum(grid, x, y - 1);
            int costFromTop = minPathSum(grid, x - 1, y);

            return Math.min(costFromLeft, costFromTop) + grid[x][y];
        }
    }
}

//    64. Minimum Path Sum
//    Medium
//    Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
//
//    Note: You can only move either down or right at any point in time.
//
//
//
//    Example 1:
//
//
//    Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
//    Output: 7
//    Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
//    Example 2:
//
//    Input: grid = [[1,2,3],[4,5,6]]
//    Output: 12
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    1 <= m, n <= 200
//    0 <= grid[i][j] <= 100