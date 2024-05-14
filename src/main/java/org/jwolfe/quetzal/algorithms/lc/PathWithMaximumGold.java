package org.jwolfe.quetzal.algorithms.lc;

public class PathWithMaximumGold {
    class Solution {
        public int getMaximumGold(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            boolean[][] visited = new boolean[m][n];
            int maxGold = 0;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] > 0) {
                        maxGold = Math.max(maxGold,
                                getPathGold(grid, m, n, i, j, visited));
                    }
                }
            }

            return maxGold;
        }

        private int getPathGold(int[][] grid, int m, int n, int row, int col, boolean[][] visited) {
            int gold = grid[row][col];
            visited[row][col] = true;

            int upGold = gold;
            int downGold = gold;
            int leftGold = gold;
            int rightGold = gold;

            if (col > 0 && grid[row][col - 1] > 0 && !visited[row][col - 1]) {
                upGold += getPathGold(grid, m, n, row, col - 1, visited);
            }

            if (col < n - 1 && grid[row][col + 1] > 0 && !visited[row][col + 1]) {
                downGold += getPathGold(grid, m, n, row, col + 1, visited);
            }

            if (row > 0 && grid[row - 1][col] > 0 && !visited[row - 1][col]) {
                leftGold += getPathGold(grid, m, n, row - 1, col, visited);
            }

            if (row < m - 1 && grid[row + 1][col] > 0 && !visited[row + 1][col]) {
                rightGold += getPathGold(grid, m, n, row + 1, col, visited);
            }

            visited[row][col] = false;
            return Math.max(upGold,
                    Math.max(downGold,
                            Math.max(leftGold, rightGold)));
        }
    }

    class Solution_Correct_1 {
        public int getMaximumGold(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            int maxGold = 0;
            boolean[][] visited = new boolean[m][n];

            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    if (grid[r][c] > 0) {
                        int gold = getMaxGold(grid, m, n, r, c, visited);
                        maxGold = Math.max(maxGold, gold);
                    }
                }
            }

            return maxGold;
        }

        private int getMaxGold(int[][] grid, int m, int n, int r, int c, boolean[][] visited) {
            int gold = grid[r][c];
            visited[r][c] = true;

            int left = gold;
            int right = gold;
            int up = gold;
            int down = gold;

            // Left
            if (r > 0 && !visited[r - 1][c] && grid[r - 1][c] > 0) {
                left += getMaxGold(grid, m, n, r - 1, c, visited);
            }

            // Right
            if (r < m - 1 && !visited[r + 1][c] && grid[r + 1][c] > 0) {
                right += getMaxGold(grid, m, n, r + 1, c, visited);
            }

            // Up
            if (c > 0 && !visited[r][c - 1] && grid[r][c - 1] > 0) {
                up += getMaxGold(grid, m, n, r, c - 1, visited);
            }

            // Down
            if (c < n - 1 && !visited[r][c + 1] && grid[r][c + 1] > 0) {
                down += getMaxGold(grid, m, n, r, c + 1, visited);
            }

            visited[r][c] = false;
            return Math.max(left,
                    Math.max(right,
                            Math.max(up, down)));
        }
    }
}

//    1219. Path with Maximum Gold
//    Medium
//    In a gold mine grid of size m x n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.
//
//    Return the maximum amount of gold you can collect under the conditions:
//
//    Every time you are located in a cell you will collect all the gold in that cell.
//    From your position, you can walk one step to the left, right, up, or down.
//    You can't visit the same cell more than once.
//    Never visit a cell with 0 gold.
//    You can start and stop collecting gold from any position in the grid that has some gold.
//
//
//    Example 1:
//
//    Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
//    Output: 24
//    Explanation:
//    [[0,6,0],
//    [5,8,7],
//    [0,9,0]]
//    Path to get the maximum gold, 9 -> 8 -> 7.
//    Example 2:
//
//    Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
//    Output: 28
//    Explanation:
//    [[1,0,7],
//    [2,0,6],
//    [3,4,5],
//    [0,3,0],
//    [9,0,20]]
//    Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    1 <= m, n <= 15
//    0 <= grid[i][j] <= 100
//    There are at most 25 cells containing gold.

