package org.jwolfe.quetzal.algorithms.lc;

public class MaxAreaOfIsland {
    class Solution {
        public int maxAreaOfIsland(int[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            int maxIslandArea = 0;
            for(int r = 0; r < m; r++) {
                for(int c = 0; c < n; c++) {
                    if(grid[r][c] == 1) {
                        int area = visitIsland(grid, m, n, r, c);
                        maxIslandArea = Math.max(maxIslandArea, area);
                    }
                }
            }

            return maxIslandArea;
        }

        private int visitIsland(int[][] grid, int m, int n, int r, int c) {
            if(r < 0 || c < 0 || r >= m || c >= n || grid[r][c] != 1) {
                return 0;
            }

            int size = 1;
            grid[r][c] = 0;

            size += visitIsland(grid, m, n, r + 1, c);
            size += visitIsland(grid, m, n, r - 1, c);
            size += visitIsland(grid, m, n, r, c + 1);
            size += visitIsland(grid, m, n, r, c - 1);

            return size;
        }
    }
}

//    695. Max Area of Island
//    Medium
//
//    You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
//
//    The area of an island is the number of cells with a value 1 in the island.
//
//    Return the maximum area of an island in grid. If there is no island, return 0.
//
//
//
//    Example 1:
//
//
//    Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
//    Output: 6
//    Explanation: The answer is not 11, because the island must be connected 4-directionally.
//    Example 2:
//
//    Input: grid = [[0,0,0,0,0,0,0,0]]
//    Output: 0
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    1 <= m, n <= 50
//    grid[i][j] is either 0 or 1.
