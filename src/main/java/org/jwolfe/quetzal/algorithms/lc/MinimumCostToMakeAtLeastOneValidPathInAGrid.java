package org.jwolfe.quetzal.algorithms.lc;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MinimumCostToMakeAtLeastOneValidPathInAGrid {
    class Solution {
        // right, left, down, down (similar to 1, 2, 3, 4)
        private final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public int minCost(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            int[][] minCosts = new int[m][n];
            for (int[] row : minCosts) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }

            Deque<int[]> deque = new ArrayDeque<>();
            deque.offerFirst(new int[]{0, 0});
            minCosts[0][0] = 0;

            while (!deque.isEmpty()) {
                int[] cell = deque.pollFirst();
                int r = cell[0];
                int c = cell[1];

                for (int i = 0; i < 4; i++) {
                    int[] direction = directions[i];
                    int nr = r + direction[0];
                    int nc = c + direction[1];
                    if (!isValid(m, n, nr, nc)) {
                        continue;
                    }

                    int cost = (grid[r][c] != i + 1) ? 1 : 0;
                    if (minCosts[nr][nc] > minCosts[r][c] + cost) {
                        minCosts[nr][nc] = minCosts[r][c] + cost;

                        if (cost == 1) {
                            deque.offerLast(new int[]{nr, nc});
                        } else {
                            deque.offerFirst(new int[]{nr, nc});
                        }
                    }
                }

            }

            return minCosts[m - 1][n - 1];
        }

        private boolean isValid(int m, int n, int row, int col) {
            return (row >= 0)
                    && (row < m)
                    && (col >= 0)
                    && (col < n);
        }
    }
}

//    1368. Minimum Cost to Make at Least One Valid Path in a Grid
//    Hard
//    Given an m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are currently in this cell. The sign of grid[i][j] can be:
//
//    1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
//    2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
//    3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
//    4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
//    Notice that there could be some signs on the cells of the grid that point outside the grid.
//
//    You will initially start at the upper left cell (0, 0). A valid path in the grid is a path that starts from the upper left cell (0, 0) and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid. The valid path does not have to be the shortest.
//
//    You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.
//
//    Return the minimum cost to make the grid have at least one valid path.
//
//
//
//    Example 1:
//
//
//    Input: grid = [[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]]
//    Output: 3
//    Explanation: You will start at point (0, 0).
//    The path to (3, 3) is as follows. (0, 0) --> (0, 1) --> (0, 2) --> (0, 3) change the arrow to down with cost = 1 --> (1, 3) --> (1, 2) --> (1, 1) --> (1, 0) change the arrow to down with cost = 1 --> (2, 0) --> (2, 1) --> (2, 2) --> (2, 3) change the arrow to down with cost = 1 --> (3, 3)
//    The total cost = 3.
//    Example 2:
//
//
//    Input: grid = [[1,1,3],[3,2,2],[1,1,4]]
//    Output: 0
//    Explanation: You can follow the path from (0, 0) to (2, 2).
//    Example 3:
//
//
//    Input: grid = [[1,2],[4,3]]
//    Output: 1
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    1 <= m, n <= 100
//    1 <= grid[i][j] <= 4