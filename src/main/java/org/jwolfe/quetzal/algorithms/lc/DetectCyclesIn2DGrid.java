package org.jwolfe.quetzal.algorithms.lc;

public class DetectCyclesIn2DGrid {
    class Solution {
        private int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public boolean containsCycle(char[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return false;
            }

            int m = grid.length;
            int n = grid[0].length;

            boolean[][] visited = new boolean[m][n];
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    if (visited[r][c]) {
                        continue;
                    }

                    if (dfs(grid, r, c, -1, -1, visited)) {
                        return true;
                    }
                }
            }

            return false;
        }

        private boolean dfs(char[][] grid, int row, int col, int prevRow, int prevCol, boolean[][] visited) {
            visited[row][col] = true;

            for (int[] dir : directions) {
                int nr = row + dir[0];
                int nc = col + dir[1];

                if (nr < 0 || nr == grid.length || nc < 0 || nc == grid[0].length || (nr == prevRow && nc == prevCol)
                        || grid[nr][nc] != grid[row][col]) {
                    continue;
                }

                if (visited[nr][nc]) {
                    return true;
                }

                if (dfs(grid, nr, nc, row, col, visited)) {
                    return true;
                }
            }

            return false;
        }
    }
}

//    1559. Detect Cycles in 2D Grid
//    Medium
//    Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.
//
//    A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell, you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.
//
//    Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.
//
//    Return true if any cycle of the same value exists in grid, otherwise, return false.
//
//
//
//    Example 1:
//
//
//
//    Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
//    Output: true
//    Explanation: There are two valid cycles shown in different colors in the image below:
//
//    Example 2:
//
//
//
//    Input: grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
//    Output: true
//    Explanation: There is only one valid cycle highlighted in the image below:
//
//    Example 3:
//
//
//
//    Input: grid = [["a","b","b"],["b","z","b"],["b","b","a"]]
//    Output: false
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    1 <= m, n <= 500
//    grid consists only of lowercase English letters.