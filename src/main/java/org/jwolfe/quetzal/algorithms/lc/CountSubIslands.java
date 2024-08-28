package org.jwolfe.quetzal.algorithms.lc;

import java.util.LinkedList;
import java.util.Queue;

public class CountSubIslands {
    class Solution {
        public int countSubIslands(int[][] grid1, int[][] grid2) {
            if (grid1 == null || grid1.length == 0 || grid1[0].length == 0 || grid2 == null || grid2.length != grid1.length || grid2[0].length != grid1[0].length) {
                return 0;
            }

            int m = grid1.length;
            int n = grid1[0].length;

            boolean[][] visited = new boolean[m][n];
            int subIslands = 0;
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    if (grid2[r][c] == 1
                            && !visited[r][c]) {
                        if (isSubIsland(grid1, grid2, m, n, r, c, visited)) {
                            subIslands++;
                        }
                    }
                }
            }

            return subIslands;
        }

        private boolean isSubIsland(int[][] grid1, int[][] grid2, int m, int n, int row, int col, boolean[][] visited) {
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{row, col});
            visited[row][col] = true;

            boolean isSubIsland = true;

            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                int r = cell[0];
                int c = cell[1];

                if (grid1[r][c] != 1) {
                    isSubIsland = false;
                }

                for (int[] direction : directions) {
                    int nr = r + direction[0];
                    int nc = c + direction[1];

                    if (nr >= 0 && nr < m && nc >= 0 && nc < n
                            && !visited[nr][nc]
                            && grid2[nr][nc] == 1) {
                        queue.offer(new int[]{nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }

            return isSubIsland;
        }

        private final int[][] directions = new int[][]{
                {-1, 0},
                {0, -1},
                {1, 0},
                {0, 1}
        };
    }
}

//    1905. Count Sub Islands
//    Medium
//    You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and 1's (representing land). An island is a group of 1's connected 4-directionally (horizontal or vertical). Any cells outside of the grid are considered water cells.
//
//    An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that make up this island in grid2.
//
//    Return the number of islands in grid2 that are considered sub-islands.
//
//
//
//    Example 1:
//
//
//    Input: grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
//    Output: 3
//    Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
//    The 1s colored red in grid2 are those considered to be part of a sub-island. There are three sub-islands.
//    Example 2:
//
//
//    Input: grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
//    Output: 2
//    Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
//    The 1s colored red in grid2 are those considered to be part of a sub-island. There are two sub-islands.
//
//
//    Constraints:
//
//    m == grid1.length == grid2.length
//    n == grid1[i].length == grid2[i].length
//    1 <= m, n <= 500
//    grid1[i][j] and grid2[i][j] are either 0 or 1.