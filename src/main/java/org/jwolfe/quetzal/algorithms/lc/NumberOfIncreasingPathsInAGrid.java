package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class NumberOfIncreasingPathsInAGrid {
    class Solution {
        private int MOD = 1_000_000_007;

        public int countPaths(int[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            int[][] dp = new int[m][n];
            for(int i = 0; i < m; i++) {
                Arrays.fill(dp[i], 1);
            }

            int[][] cellList = new int[m * n][2];
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    int index = i * n + j;
                    cellList[index][0] = i;
                    cellList[index][1] = j;
                }
            }

            Arrays.sort(cellList, (a, b) -> grid[b[0]][b[1]] - grid[a[0]][a[1]]);

            for(int[] cell : cellList) {
                int i = cell[0];
                int j = cell[1];

                if( i > 0 && grid[i - 1][j] > grid[i][j]) {
                    dp[i][j] += dp[i - 1][j];
                    dp[i][j] %= MOD;
                }

                if(i < m - 1 && grid[i + 1][j] > grid[i][j]) {
                    dp[i][j] += dp[i + 1][j];
                    dp[i][j] %= MOD;
                }

                if(j > 0 && grid[i][j - 1] > grid[i][j]) {
                    dp[i][j] += dp[i][j - 1];
                    dp[i][j] %= MOD;
                }

                if(j < n - 1 && grid[i][j + 1] > grid[i][j]) {
                    dp[i][j] += dp[i][j + 1];
                    dp[i][j] %= MOD;
                }
            }

            int totalWays = 0;

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    totalWays += dp[i][j];
                    totalWays %= MOD;
                }
            }

            return totalWays;
        }

        private void print(int[][] arr) {
            for(int[] row : arr) {
                for(int cell : row) {
                    System.out.print(cell + " ");
                }

                System.out.println();
            }

            System.out.println();
        }
    }

    class Solution_Incorrect {
        private int MOD = 1_000_000_007;

        public int countPaths(int[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            int[][] dp = new int[m][n];
            int totalWays = 0;

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    int ways = 1;

                    if( i > 0 && grid[i - 1][j] > grid[i][j]) {
                        ways += dp[i - 1][j];
                    }

                    if(i < m - 1 && grid[i + 1][j] > grid[i][j]) {
                        ways += dp[i + 1][j];
                    }

                    if(j > 0 && grid[i][j - 1] > grid[i][j]) {
                        ways += dp[i][j - 1];
                    }

                    if(j < n - 1 && grid[i][j + 1] > grid[i][j]) {
                        ways += dp[i][j + 1];
                    }

                    dp[i][j] = ways;
                    totalWays += ways;
                }
            }

            return totalWays;
        }
    }
}

//    2328. Number of Increasing Paths in a Grid
//    Hard
//    You are given an m x n integer matrix grid, where you can move from a cell to any adjacent cell in all 4 directions.
//
//    Return the number of strictly increasing paths in the grid such that you can start from any cell and end at any cell. Since the answer may be very large, return it modulo 109 + 7.
//
//    Two paths are considered different if they do not have exactly the same sequence of visited cells.
//
//
//
//    Example 1:
//
//
//    Input: grid = [[1,1],[3,4]]
//    Output: 8
//    Explanation: The strictly increasing paths are:
//    - Paths with length 1: [1], [1], [3], [4].
//    - Paths with length 2: [1 -> 3], [1 -> 4], [3 -> 4].
//    - Paths with length 3: [1 -> 3 -> 4].
//    The total number of paths is 4 + 3 + 1 = 8.
//    Example 2:
//
//    Input: grid = [[1],[2]]
//    Output: 3
//    Explanation: The strictly increasing paths are:
//    - Paths with length 1: [1], [2].
//    - Paths with length 2: [1 -> 2].
//    The total number of paths is 2 + 1 = 3.
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    1 <= m, n <= 1000
//    1 <= m * n <= 105
//    1 <= grid[i][j] <= 105