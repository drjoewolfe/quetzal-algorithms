package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MinimumObstacleRemovalToReachCorner {
    class Solution {
        private int[][] directions = new int[][]{
                {-1, 0},
                {0, -1},
                {1, 0},
                {0, 1}
        };

        public int minimumObstacles(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            int[][] minObstacles = new int[m][n];
            for (int r = 0; r < m; r++) {
                Arrays.fill(minObstacles[r], Integer.MAX_VALUE);
            }

            minObstacles[0][0] = grid[0][0];

            PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
            heap.offer(new int[]{grid[0][0], 0, 0});

            while (!heap.isEmpty()) {
                int[] element = heap.poll();
                int obstacles = element[0];
                int r = element[1];
                int c = element[2];

                for (int[] direction : directions) {
                    int nr = r + direction[0];
                    int nc = c + direction[1];

                    if (nr < 0 || nr == m || nc < 0 || nc == n) {
                        continue;
                    }

                    int newObstacles = obstacles + grid[nr][nc];
                    if (nr == m - 1 && nc == n - 1) {
                        return newObstacles;
                    }

                    if (minObstacles[nr][nc] > newObstacles) {
                        minObstacles[nr][nc] = newObstacles;
                        heap.offer(new int[]{minObstacles[nr][nc], nr, nc});
                    }
                }
            }

            return minObstacles[m - 1][n - 1];
        }
    }
}

//    2290. Minimum Obstacle Removal to Reach Corner
//    Hard
//    You are given a 0-indexed 2D integer array grid of size m x n. Each cell has one of two values:
//
//    0 represents an empty cell,
//    1 represents an obstacle that may be removed.
//    You can move up, down, left, or right from and to an empty cell.
//
//    Return the minimum number of obstacles to remove so you can move from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1).
//
//
//
//    Example 1:
//
//
//    Input: grid = [[0,1,1],[1,1,0],[1,1,0]]
//    Output: 2
//    Explanation: We can remove the obstacles at (0, 1) and (0, 2) to create a path from (0, 0) to (2, 2).
//    It can be shown that we need to remove at least 2 obstacles, so we return 2.
//    Note that there may be other ways to remove 2 obstacles to create a path.
//    Example 2:
//
//
//    Input: grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]]
//    Output: 0
//    Explanation: We can move from (0, 0) to (2, 4) without removing any obstacles, so we return 0.
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    1 <= m, n <= 105
//    2 <= m * n <= 105
//    grid[i][j] is either 0 or 1.
//    grid[0][0] == grid[m - 1][n - 1] == 0