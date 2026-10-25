package org.jwolfe.quetzal.algorithms.lc;

public class NumberOfClosedIslands {
    class Solution {
        public int closedIsland(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            for (int r = 0; r < m; r++) {
                if (grid[r][0] == 0) {
                    mark(grid, m, n, r, 0);
                }

                if (grid[r][n - 1] == 0) {
                    mark(grid, m, n, r, n - 1);
                }
            }

            for (int c = 0; c < n; c++) {
                if (grid[0][c] == 0) {
                    mark(grid, m, n, 0, c);
                }

                if (grid[m - 1][c] == 0) {
                    mark(grid, m, n, m - 1, c);
                }
            }

            int islands = 0;
            for (int r = 1; r < m - 1; r++) {
                for (int c = 1; c < n - 1; c++) {
                    if (grid[r][c] == 0) {
                        islands++;
                        mark(grid, m, n, r, c);
                    }
                }
            }

            return islands;
        }

        private void mark(int[][] grid, int m, int n, int row, int col) {
            if (row < 0 || row == m || col < 0 || col == n || grid[row][col] != 0) {
                return;
            }

            grid[row][col] = 2;
            mark(grid, m, n, row + 1, col);
            mark(grid, m, n, row - 1, col);
            mark(grid, m, n, row, col + 1);
            mark(grid, m, n, row, col - 1);
        }
    }
}

//    1254. Number of Closed Islands
//    Medium
//    Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
//
//    Return the number of closed islands.
//
//
//
//    Example 1:
//
//
//
//    Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
//    Output: 2
//    Explanation:
//    Islands in gray are closed because they are completely surrounded by water (group of 1s).
//    Example 2:
//
//
//
//    Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
//    Output: 1
//    Example 3:
//
//    Input: grid = [[1,1,1,1,1,1,1],
//    [1,0,0,0,0,0,1],
//    [1,0,1,1,1,0,1],
//    [1,0,1,0,1,0,1],
//    [1,0,1,1,1,0,1],
//    [1,0,0,0,0,0,1],
//    [1,1,1,1,1,1,1]]
//    Output: 2
//
//
//    Constraints:
//
//    1 <= grid.length, grid[0].length <= 100
//    0 <= grid[i][j] <=1
