package org.jwolfe.quetzal.algorithms.lc;

public class MinimumPathCostInAGrid {
    class Solution {
        public int minPathCost(int[][] grid, int[][] moveCost) {
            if(grid == null || moveCost == null || grid.length == 0 || moveCost.length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            int[][] dp = new int[m][n];
            for(int j = 0; j < n; j++) {
                dp[m - 1][j] = grid[m - 1][j];
            }

            for(int i = m - 2; i >= 0; i--) {
                for(int j = 0; j < n; j++) {
                    int minCost = Integer.MAX_VALUE;
                    for(int k = 0; k < n; k++) {
                        minCost = Math.min(minCost,
                                grid[i][j] + moveCost[grid[i][j]][k] + dp[i + 1][k]);
                    }

                    dp[i][j] = minCost;
                }
            }

            int minCost = Integer.MAX_VALUE;
            for(int j = 0; j < n; j++) {
                minCost = Math.min(minCost, dp[0][j]);
            }

            return minCost;
        }
    }

    class Solution_Memoized {
        public int minPathCost(int[][] grid, int[][] moveCost) {
            if(grid == null || moveCost == null || grid.length == 0 || moveCost.length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            int minCost = Integer.MAX_VALUE;

            Integer[][] memo = new Integer[m][n];
            for(int i = 0; i < n; i++) {
                minCost = Math.min(minCost, minPathCost(grid, moveCost, m, n, 0, i, memo));
            }

            return minCost;
        }

        private int minPathCost(int[][] grid, int[][] moveCost, int m, int n, int row, int col, Integer[][] memo) {
            if(row == m - 1) {
                return grid[row][col];
            }

            if(memo[row][col] != null) {
                return memo[row][col];
            }

            int fromCell = grid[row][col];

            int minCost = Integer.MAX_VALUE;
            for(int i = 0; i < n; i++) {
                int pathCost = grid[row][col] + moveCost[fromCell][i] + minPathCost(grid, moveCost, m, n, row + 1, i, memo);
                minCost = Math.min(minCost, pathCost);
            }

            memo[row][col] = minCost;
            return minCost;
        }
    }

    class Solution_Recursive {
        public int minPathCost(int[][] grid, int[][] moveCost) {
            if(grid == null || moveCost == null || grid.length == 0 || moveCost.length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            int minCost = Integer.MAX_VALUE;
            for(int i = 0; i < n; i++) {
                minCost = Math.min(minCost, minPathCost(grid, moveCost, m, n, 0, i));
            }

            return minCost;
        }

        private int minPathCost(int[][] grid, int[][] moveCost, int m, int n, int row, int col) {
            if(row == m - 1) {
                return grid[row][col];
            }

            int fromCell = grid[row][col];

            int minCost = Integer.MAX_VALUE;
            for(int i = 0; i < n; i++) {
                int pathCost = grid[row][col] + moveCost[fromCell][i] + minPathCost(grid, moveCost, m, n, row + 1, i);
                minCost = Math.min(minCost, pathCost);
            }

            return minCost;
        }
    }

    class Solution_Brute {
        int minCost;

        public int minPathCost(int[][] grid, int[][] moveCost) {
            if(grid == null || moveCost == null || grid.length == 0 || moveCost.length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            minCost = Integer.MAX_VALUE;
            for(int i = 0; i < n; i++) {
                minPathCost(grid, moveCost, m, n, 0, i, 0);
            }

            return minCost;
        }

        private void minPathCost(int[][] grid, int[][] moveCost, int m, int n, int row, int col, int cost) {
            if(row == m - 1) {
                // Last row reached
                minCost = Math.min(minCost, cost + grid[row][col]);
                return;
            }

            int fromCell = grid[row][col];

            for(int i = 0; i < n; i++) {
                minPathCost(grid, moveCost, m, n, row + 1, i, cost + grid[row][col] + moveCost[fromCell][i]);
            }
        }
    }

// [[5,3],[4,0],[2,1]]
// [[9,8],[1,5],[10,12],[18,6],[2,4],[14,3]]
}

//    2304. Minimum Path Cost in a Grid
//    Medium
//    You are given a 0-indexed m x n integer matrix grid consisting of distinct integers from 0 to m * n - 1. You can move in this matrix from a cell to any other cell in the next row. That is, if you are in cell (x, y) such that x < m - 1, you can move to any of the cells (x + 1, 0), (x + 1, 1), ..., (x + 1, n - 1). Note that it is not possible to move from cells in the last row.
//
//    Each possible move has a cost given by a 0-indexed 2D array moveCost of size (m * n) x n, where moveCost[i][j] is the cost of moving from a cell with value i to a cell in column j of the next row. The cost of moving from cells in the last row of grid can be ignored.
//
//    The cost of a path in grid is the sum of all values of cells visited plus the sum of costs of all the moves made. Return the minimum cost of a path that starts from any cell in the first row and ends at any cell in the last row.
//
//
//
//    Example 1:
//
//
//    Input: grid = [[5,3],[4,0],[2,1]], moveCost = [[9,8],[1,5],[10,12],[18,6],[2,4],[14,3]]
//    Output: 17
//    Explanation: The path with the minimum possible cost is the path 5 -> 0 -> 1.
//    - The sum of the values of cells visited is 5 + 0 + 1 = 6.
//    - The cost of moving from 5 to 0 is 3.
//    - The cost of moving from 0 to 1 is 8.
//    So the total cost of the path is 6 + 3 + 8 = 17.
//    Example 2:
//
//    Input: grid = [[5,1,2],[4,0,3]], moveCost = [[12,10,15],[20,23,8],[21,7,1],[8,1,13],[9,10,25],[5,3,2]]
//    Output: 6
//    Explanation: The path with the minimum possible cost is the path 2 -> 3.
//    - The sum of the values of cells visited is 2 + 3 = 5.
//    - The cost of moving from 2 to 3 is 1.
//    So the total cost of this path is 5 + 1 = 6.
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    2 <= m, n <= 50
//    grid consists of distinct integers from 0 to m * n - 1.
//    moveCost.length == m * n
//    moveCost[i].length == n
//    1 <= moveCost[i][j] <= 100