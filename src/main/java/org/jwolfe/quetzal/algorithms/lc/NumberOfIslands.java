package org.jwolfe.quetzal.algorithms.lc;

public class NumberOfIslands {
    class Solution {
        public int numIslands(char[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int islands = 0;
            for(int r = 0; r < grid.length; r++) {
                for(int c = 0; c < grid[0].length; c++) {
                    if(grid[r][c] == '1') {
                        islands++;
                        visitIsland(grid, r, c);
                    }
                }
            }

            return islands;
        }

        private void visitIsland(char[][] grid, int r, int c) {
            if(r < 0 || c < 0 || r == grid.length || c == grid[0].length || grid[r][c] == '0') {
                return;
            }

            grid[r][c] = '0';
            visitIsland(grid, r + 1, c);
            visitIsland(grid, r - 1, c);
            visitIsland(grid, r, c + 1);
            visitIsland(grid, r, c - 1);
        }
    }

    class Solution_Correct_1 {
        public int numIslands(char[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int rows = grid.length;
            int columns = grid[0].length;

            for(int i = 0; i < rows; i++) {
                if(grid[i].length != columns) {
                    return 0;
                }
            }

            int islands = 0;
            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < columns; j++) {
                    if(grid[i][j] == '1') {
                        islands++;
                        visitIsland(grid, rows, columns, i, j);
                    }
                }
            }

            return islands;
        }

        private void visitIsland(char[][] grid, int rows, int columns, int i, int j) {
            if( i < 0 || i >= rows || j < 0 || j >= columns) {
                return;
            }

            if(grid[i][j] != '1') {
                return;
            }

            grid[i][j] = '0';

            visitIsland(grid, rows, columns, i - 1, j);
            visitIsland(grid, rows, columns, i + 1, j);
            visitIsland(grid, rows, columns, i, j - 1);
            visitIsland(grid, rows, columns, i, j + 1);
        }
    }
}

//    200. Number of Islands
//    Medium
//    Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
//
//    An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
//
//
//
//    Example 1:
//
//    Input: grid = [
//    ["1","1","1","1","0"],
//    ["1","1","0","1","0"],
//    ["1","1","0","0","0"],
//    ["0","0","0","0","0"]
//    ]
//    Output: 1
//    Example 2:
//
//    Input: grid = [
//    ["1","1","0","0","0"],
//    ["1","1","0","0","0"],
//    ["0","0","1","0","0"],
//    ["0","0","0","1","1"]
//    ]
//    Output: 3
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    1 <= m, n <= 300
//    grid[i][j] is '0' or '1'.