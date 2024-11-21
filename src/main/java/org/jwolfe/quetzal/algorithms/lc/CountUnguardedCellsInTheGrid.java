package org.jwolfe.quetzal.algorithms.lc;

public class CountUnguardedCellsInTheGrid {
    class Solution {

        private static final int UNGUARDED = 0;
        private static final int GUARDED = 1;
        private static final int GUARD = 2;
        private static final int WALL = 3;

        public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
            if (m == 0 || n == 0) {
                return 0;
            }

            int[][] grid = new int[m][n];

            for (int[] wall : walls) {
                grid[wall[0]][wall[1]] = WALL;
            }

            for (int[] guard : guards) {
                grid[guard[0]][guard[1]] = GUARD;
            }

            for (int[] guard : guards) {
                markGuarded(grid, guard[0], guard[1]);
            }

            int count = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int cell = grid[i][j];
                    if (cell == UNGUARDED) {
                        count++;
                    }
                }
            }

            return count;
        }

        private void markGuarded(int[][] grid, int row, int col) {
            for (int r = row - 1; r >= 0; r--) {
                if (grid[r][col] == WALL || grid[r][col] == GUARD) break;
                grid[r][col] = GUARDED;
            }

            for (int r = row + 1; r < grid.length; r++) {
                if (grid[r][col] == WALL || grid[r][col] == GUARD) break;
                grid[r][col] = GUARDED;
            }

            for (int c = col - 1; c >= 0; c--) {
                if (grid[row][c] == WALL || grid[row][c] == GUARD) break;
                grid[row][c] = GUARDED;
            }

            for (int c = col + 1; c < grid[0].length; c++) {
                if (grid[row][c] == WALL || grid[row][c] == GUARD) break;
                grid[row][c] = GUARDED;
            }
        }

        private void print(int[][] arr) {
            for (int[] a : arr) {
                System.out.print("[");
                for (int b : a) {
                    System.out.print(b + " ");
                }
                System.out.print("] ");
            }

            System.out.println();
        }
    }

    class Solution_TLE {
        public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
            if (m == 0 || n == 0) {
                return 0;
            }

            int[][] grid = new int[m][n];
            for (int[] wall : walls) {
                int i = wall[0];
                int j = wall[1];

                grid[i][j] = 2;
            }

            for (int[] guard : guards) {
                int row = guard[0];
                int col = guard[1];

                grid[row][col] = 1;

                for (int j = col - 1; j >= 0; j--) {
                    if (grid[row][j] == 2) {
                        break;
                    }

                    grid[row][j] = 1;
                }

                for (int j = col + 1; j < n; j++) {
                    if (grid[row][j] == 2) {
                        break;
                    }

                    grid[row][j] = 1;
                }

                for (int i = row - 1; i >= 0; i--) {
                    if (grid[i][col] == 2) {
                        break;
                    }

                    grid[i][col] = 1;
                }

                for (int i = row + 1; i < m; i++) {
                    if (grid[i][col] == 2) {
                        break;
                    }

                    grid[i][col] = 1;
                }
            }

            int count = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0) {
                        count++;
                    }
                }
            }

            return count;
        }

        private void print(int[][] arr) {
            for (int[] a : arr) {
                System.out.print("[");
                for (int b : a) {
                    System.out.print(b + " ");
                }
                System.out.print("] ");
            }

            System.out.println();
        }
    }


// [1 2 0 1 0 0 ] [1 1 1 1 2 0 ] [1 1 2 1 1 1 ] [1 1 0 1 0 0 ]
}

//    2257. Count Unguarded Cells in the Grid
//    Solved
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