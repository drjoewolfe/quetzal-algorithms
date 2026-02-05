package org.jwolfe.quetzal.algorithms.lc;

import java.util.LinkedList;
import java.util.Queue;

public class CountUnguardedCellsInTheGrid {
    class Solution {
        private final int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        private final int EMPTY = 0, GUARD = 1, WALL = 2, GUARDED = 3;

        public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
            if (m == 0 || n == 0) {
                return 0;
            }

            if (guards == null || guards.length == 0) {
                return m * n;
            }

            int[][] grid = new int[m][n];
            for (int[] guard : guards) {
                int r = guard[0];
                int c = guard[1];

                grid[r][c] = GUARD;
            }

            for (int[] wall : walls) {
                int r = wall[0];
                int c = wall[1];

                grid[r][c] = WALL;
            }

            for (int[] guard : guards) {
                int r = guard[0];
                int c = guard[1];

                for (int[] direction : directions) {
                    int nr = r + direction[0];
                    int nc = c + direction[1];

                    while (nr >= 0 && nr < m && nc >= 0 && nc < n
                            && grid[nr][nc] != GUARD
                            && grid[nr][nc] != WALL) {
                        grid[nr][nc] = GUARDED;
                        nr = nr + direction[0];
                        nc = nc + direction[1];
                    }
                }
            }

            int count = 0;
            for (int[] row : grid) {
                for (int cell : row) {
                    if (cell == EMPTY) {
                        count++;
                    }
                }
            }

            return count;
        }
    }

    class Solution_Incorrect {
        private int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
            if (m == 0 || n == 0) {
                return 0;
            }

            if (guards == null || guards.length == 0) {
                return m * n;
            }

            int[][] grid = new int[m][n];
            boolean[][] visited = new boolean[m][n];

            Queue<int[]> queue = new LinkedList<>();
            for (int[] guard : guards) {
                int r = guard[0];
                int c = guard[1];

                grid[r][c] = 1;
                queue.offer(guard);
                visited[r][c] = true;
            }

            for (int[] wall : walls) {
                int r = wall[0];
                int c = wall[1];

                grid[r][c] = 2;
                visited[r][c] = true;
            }

            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                int r = cell[0];
                int c = cell[1];

                for (int[] direction : directions) {
                    int nr = r + direction[0];
                    int nc = c + direction[1];

                    if (nr < 0 || nr == m || nc < 0 || nc == n || visited[nr][nc]) {
                        continue;
                    }

                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }

            int count = 0;
            for (int[] row : grid) {
                for (int cell : row) {
                    if (cell == 0) {
                        count++;
                    }
                }
            }

            return count;
        }
    }
}

//    2257. Count Unguarded Cells in the Grid
//    Medium
//    You are given two integers m and n representing a 0-indexed m x n grid. You are also given two 2D integer arrays guards and walls where guards[i] = [rowi, coli] and walls[j] = [rowj, colj] represent the positions of the ith guard and jth wall respectively.
//
//    A guard can see every cell in the four cardinal directions (north, east, south, or west) starting from their position unless obstructed by a wall or another guard. A cell is guarded if there is at least one guard that can see it.
//
//    Return the number of unoccupied cells that are not guarded.
//
//
//
//    Example 1:
//
//
//    Input: m = 4, n = 6, guards = [[0,0],[1,1],[2,3]], walls = [[0,1],[2,2],[1,4]]
//    Output: 7
//    Explanation: The guarded and unguarded cells are shown in red and green respectively in the above diagram.
//    There are a total of 7 unguarded cells, so we return 7.
//    Example 2:
//
//
//    Input: m = 3, n = 3, guards = [[1,1]], walls = [[0,1],[1,0],[2,1],[1,2]]
//    Output: 4
//    Explanation: The unguarded cells are shown in green in the above diagram.
//    There are a total of 4 unguarded cells, so we return 4.
//
//
//    Constraints:
//
//    1 <= m, n <= 105
//    2 <= m * n <= 105
//    1 <= guards.length, walls.length <= 5 * 104
//    2 <= guards.length + walls.length <= m * n
//    guards[i].length == walls[j].length == 2
//    0 <= rowi, rowj < m
//    0 <= coli, colj < n
//    All the positions in guards and walls are unique.