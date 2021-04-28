package org.jwolfe.quetzal.algorithms.lc;

public class UniquePathsII {
    class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            if(obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
                return 0;
            }

            int m = obstacleGrid.length;
            int n = obstacleGrid[0].length;

            if(obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
                return 0;
            }

            int[][] dp = new int[m][n];

            dp[0][0] = 1;
            // First row
            for(int j = 1; j < n; j++) {
                dp[0][j] = (obstacleGrid[0][j] == 0 && dp[0][j - 1] == 1) ? 1 : 0;
            }

            // First Column
            for(int i = 1; i < m; i++) {
                dp[i][0] = (obstacleGrid[i][0] == 0 && dp[i - 1][0] == 1) ? 1 : 0;
            }

            // Rest of the grid
            for(int i = 1; i < m; i++) {
                for(int j = 1; j < n; j++) {
                    dp[i][j] = obstacleGrid[i][j] == 0 ? dp[i - 1][j] + dp[i][j - 1] : 0;
                }
            }

            return dp[m - 1][n - 1];
        }
    }

    class Solution_Recursive {
        int paths;

        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            if(obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
                return 0;
            }

            int m = obstacleGrid.length;
            int n = obstacleGrid[0].length;

            if(obstacleGrid[0][0] == 1) {
                return 0;
            }

            paths = 0;
            computeUniquePaths(obstacleGrid, m, n, 0, 0);

            return paths;
        }

        private void computeUniquePaths(int[][] obstacleGrid, int m, int n, int i, int j) {
            if(i == m - 1 && j == n - 1) {
                paths++;
                return;
            }

            // Right
            if(j < n - 1 && obstacleGrid[i][j + 1] == 0) {
                computeUniquePaths(obstacleGrid, m, n, i, j + 1);
            }

            // Down
            if(i < m - 1 && obstacleGrid[i + 1][j] == 0) {
                computeUniquePaths(obstacleGrid, m, n, i + 1, j);
            }
        }
    }

// [[0,0,0],[0,1,0],[0,0,0]]
}

//    63. Unique Paths II
//    Medium
//    A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
//
//    The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
//
//    Now consider if some obstacles are added to the grids. How many unique paths would there be?
//
//    An obstacle and space is marked as 1 and 0 respectively in the grid.
//
//
//
//    Example 1:
//
//
//    Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
//    Output: 2
//    Explanation: There is one obstacle in the middle of the 3x3 grid above.
//    There are two ways to reach the bottom-right corner:
//    1. Right -> Right -> Down -> Down
//    2. Down -> Down -> Right -> Right
//    Example 2:
//
//
//    Input: obstacleGrid = [[0,1],[0,0]]
//    Output: 1
//
//
//    Constraints:
//
//    m == obstacleGrid.length
//    n == obstacleGrid[i].length
//    1 <= m, n <= 100
//    obstacleGrid[i][j] is 0 or 1.