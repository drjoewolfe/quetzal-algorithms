package org.jwolfe.quetzal.algorithms.lc;

import java.util.PriorityQueue;

public class MinimumTimeToVisitACellInAGrid {
    class Solution {
        private int[][] directions = new int[][]{
                {-1, 0},
                {0, -1},
                {1, 0},
                {0, 1}
        };

        public int minimumTime(int[][] grid) {
            if (grid == null || grid.length < 2 || grid[0].length < 2) {
                return -1;
            }

            if (grid[0][1] > 1 && grid[1][0] > 1) {
                return -1;
            }

            int m = grid.length;
            int n = grid[0].length;

            PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
            heap.offer(new int[]{grid[0][0], 0, 0});

            boolean[][] visited = new boolean[m][n];
            visited[0][0] = true;

            while (!heap.isEmpty()) {
                int[] element = heap.poll();
                int time = element[0];
                int row = element[1];
                int col = element[2];

                if (row == m - 1 && col == n - 1) {
                    return time;
                }

                for (int[] direction : directions) {
                    int nextRow = row + direction[0];
                    int nextCol = col + direction[1];

                    if (nextRow < 0 || nextRow == m
                            || nextCol < 0 || nextCol == n
                            || visited[nextRow][nextCol]) {
                        continue;
                    }

                    int unlockTime = grid[nextRow][nextCol];
                    int extraTimeWasted = ((unlockTime - time) % 2 == 0) ? 1 : 0;

                    int nextTime = Math.max(time + 1, unlockTime + extraTimeWasted);
                    heap.offer(new int[]{nextTime, nextRow, nextCol});
                    visited[nextRow][nextCol] = true;
                }
            }

            return -1;
        }
    }

// [[0,1,3,2],[5,1,2,5],[4,3,8,6]]
}

//    2577. Minimum Time to Visit a Cell In a Grid
//    Hard
//    You are given a m x n matrix grid consisting of non-negative integers where grid[row][col] represents the minimum time required to be able to visit the cell (row, col), which means you can visit the cell (row, col) only when the time you visit it is greater than or equal to grid[row][col].
//
//    You are standing in the top-left cell of the matrix in the 0th second, and you must move to any adjacent cell in the four directions: up, down, left, and right. Each move you make takes 1 second.
//
//    Return the minimum time required in which you can visit the bottom-right cell of the matrix. If you cannot visit the bottom-right cell, then return -1.
//
//
//
//    Example 1:
//
//
//
//    Input: grid = [[0,1,3,2],[5,1,2,5],[4,3,8,6]]
//    Output: 7
//    Explanation: One of the paths that we can take is the following:
//    - at t = 0, we are on the cell (0,0).
//    - at t = 1, we move to the cell (0,1). It is possible because grid[0][1] <= 1.
//    - at t = 2, we move to the cell (1,1). It is possible because grid[1][1] <= 2.
//    - at t = 3, we move to the cell (1,2). It is possible because grid[1][2] <= 3.
//    - at t = 4, we move to the cell (1,1). It is possible because grid[1][1] <= 4.
//    - at t = 5, we move to the cell (1,2). It is possible because grid[1][2] <= 5.
//    - at t = 6, we move to the cell (1,3). It is possible because grid[1][3] <= 6.
//    - at t = 7, we move to the cell (2,3). It is possible because grid[2][3] <= 7.
//    The final time is 7. It can be shown that it is the minimum time possible.
//    Example 2:
//
//
//
//    Input: grid = [[0,2,4],[3,2,1],[1,0,4]]
//    Output: -1
//    Explanation: There is no path from the top left to the bottom-right cell.
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    2 <= m, n <= 1000
//    4 <= m * n <= 105
//    0 <= grid[i][j] <= 105
//    grid[0][0] == 0