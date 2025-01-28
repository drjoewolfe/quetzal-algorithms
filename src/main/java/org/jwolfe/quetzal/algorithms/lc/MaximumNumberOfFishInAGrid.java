package org.jwolfe.quetzal.algorithms.lc;

public class MaximumNumberOfFishInAGrid {
    class Solution {
        public int findMaxFish(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            int maxFish = 0;
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    if (grid[r][c] == 0) {
                        continue;
                    }
                    boolean[][] visited = new boolean[m][n];
                    int fish = dfs(grid, m, n, r, c, visited);
                    maxFish = Math.max(maxFish, fish);
                }
            }

            return maxFish;
        }

        private int dfs(int[][] grid, int m, int n, int r, int c, boolean[][] visited) {
            if (r < 0 || r == m || c < 0 || c == n || visited[r][c] || grid[r][c] == 0) {
                return 0;
            }

            int fish = grid[r][c];
            visited[r][c] = true;

            fish += dfs(grid, m, n, r - 1, c, visited);
            fish += dfs(grid, m, n, r + 1, c, visited);
            fish += dfs(grid, m, n, r, c - 1, visited);
            fish += dfs(grid, m, n, r, c + 1, visited);

            return fish;
        }
    }
}

//    2658. Maximum Number of Fish in a Grid
//    Medium
//    You are given a 0-indexed 2D matrix grid of size m x n, where (r, c) represents:
//
//    A land cell if grid[r][c] = 0, or
//    A water cell containing grid[r][c] fish, if grid[r][c] > 0.
//    A fisher can start at any water cell (r, c) and can do the following operations any number of times:
//
//    Catch all the fish at cell (r, c), or
//    Move to any adjacent water cell.
//    Return the maximum number of fish the fisher can catch if he chooses his starting cell optimally, or 0 if no water cell exists.
//
//    An adjacent cell of the cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) or (r - 1, c) if it exists.
//
//
//
//    Example 1:
//
//
//    Input: grid = [[0,2,1,0],[4,0,0,3],[1,0,0,4],[0,3,2,0]]
//    Output: 7
//    Explanation: The fisher can start at cell (1,3) and collect 3 fish, then move to cell (2,3) and collect 4 fish.
//    Example 2:
//
//
//    Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,1]]
//    Output: 1
//    Explanation: The fisher can start at cells (0,0) or (3,3) and collect a single fish.
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    1 <= m, n <= 10
//    0 <= grid[i][j] <= 10