package org.jwolfe.quetzal.algorithms.lc;

public class NumberOfEnclaves {
    class Solution {
        public int numEnclaves(int[][] grid) {
            if(grid == null || grid.length < 2 || grid[0].length < 2) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            boolean[][] visited = new boolean[m][n];
            for(int i = 0; i < m; i++) {
                if(grid[i][0] == 1 && !visited[i][0]) {
                    mark(grid, m, n, i, 0, visited);
                }

                if(grid[i][n - 1] == 1 && !visited[i][n - 1]) {
                    mark(grid, m, n, i, n - 1, visited);
                }
            }

            for(int j = 0; j < n; j++) {
                if(grid[0][j] == 1 && !visited[0][j]) {
                    mark(grid, m, n, 0, j, visited);
                }

                if(grid[m - 1][j] == 1 && !visited[m - 1][j]) {
                    mark(grid, m, n, m - 1, j, visited);
                }
            }

            int count = 0;
            for(int i = 1; i < m - 1; i++) {
                for(int j = 1; j < n - 1; j++) {
                    if(grid[i][j] == 1 && !visited[i][j]) {
                        count++;
                    }
                }
            }

            return count;
        }

        private void mark(int[][] grid, int m, int n, int i, int j, boolean[][] visited) {
            if(i < 0 || i >= m || j < 0 || j >= n
                    || grid[i][j] != 1
                    || visited[i][j]) {
                return;
            }

            visited[i][j] = true;
            mark(grid, m, n, i - 1, j, visited);
            mark(grid, m, n, i + 1, j, visited);
            mark(grid, m, n, i, j - 1, visited);
            mark(grid, m, n, i, j + 1, visited);
        }
    }

    class Solution_Correct_1 {
        public int numEnclaves(int[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            // Walk through the boundaries & mark
            for(int r = 0; r < m; r++) {
                if(grid[r][0] == 1) {
                    markRegion(grid, m, n, r, 0);
                }

                if(grid[r][n - 1] == 1) {
                    markRegion(grid, m, n, r, n - 1);
                }
            }

            for(int c = 1; c < n - 1; c++) {
                if(grid[0][c] == 1) {
                    markRegion(grid, m, n, 0, c);
                }

                if(grid[m - 1][c] == 1) {
                    markRegion(grid, m, n, m - 1, c);
                }
            }

            int count = 0;
            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {
                    if(grid[r][c] == 1) {
                        count++;
                    }
                }
            }

            return count;
        }

        private void markRegion(int[][] grid, int m, int n, int row, int col) {
            if(row < 0 || row >= m || col < 0 || col >= n
                    || grid[row][col] != 1) {
                return;
            }

            grid[row][col] = 2;
            markRegion(grid, m, n, row + 1, col);
            markRegion(grid, m, n, row - 1, col);
            markRegion(grid, m, n, row, col + 1);
            markRegion(grid, m, n, row, col - 1);
        }
    }

// [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
// [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
}

//    1020. Number of Enclaves
//    Medium
//    You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
//
//    A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.
//
//    Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.
//
//
//
//    Example 1:
//
//
//    Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
//    Output: 3
//    Explanation: There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.
//    Example 2:
//
//
//    Input: grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
//    Output: 0
//    Explanation: All 1s are either on the boundary or can reach the boundary.
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    1 <= m, n <= 500
//    grid[i][j] is either 0 or 1.