package org.jwolfe.quetzal.algorithms.lc;

import java.util.HashSet;
import java.util.Set;

public class SwimInRisingWater {
    class Solution {
        private int[][] directions = new int[][] { {0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        public int swimInWater(int[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length != grid.length) {
                return 0;
            }

            int n = grid.length;
            int minTime = n * n - 1;
            int left = Math.max(grid[0][0], grid[n - 1][n - 1]);
            int right = n * n - 1;

            while(left <= right) {
                int mid = left + (right - left) / 2;

                if(canSwimInWater(grid, n, 0, 0, mid, new HashSet<>())) {
                    minTime = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return minTime;
        }

        private boolean canSwimInWater(int[][] grid, int n, int row, int column, int max, Set<Integer> stack) {
            if(row < 0 || row == n || column < 0 || column == n) {
                return false;
            }

            if(grid[row][column] > max) {
                return false;
            }

            int cell = row * n + column;
            if(stack.contains(cell)) {
                return false;
            }

            if(row == n - 1 && column == n - 1) {
                return true;
            }

            stack.add(cell);

            for(int i = 0; i < directions.length; i++) {
                int[] pointer = directions[i];
                int r = row + pointer[0];
                int c = column + pointer[1];

                stack.add(cell);
                if(canSwimInWater(grid, n, r, c, max, stack)) {
                    return true;
                }
            }

            return false;
        }
    }

    class Solution_Brute {
        public int swimInWater(int[][] grid) {
            if(grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            return swimInWater(grid, m, n, 0, 0, Integer.MIN_VALUE, new HashSet<Integer>());
        }

        private int swimInWater(int[][] grid, int m, int n, int row, int column, int largestWaterLevel, Set<Integer> stack) {
            if(row == m - 1 && column == n - 1) {
                // Reached bottom right
                return Math.max(largestWaterLevel, grid[row][column]);
            }

            if(row < 0 || row == m || column < 0 || column == n) {
                return Integer.MAX_VALUE;
            }

            int minWaterLevel = Integer.MAX_VALUE;

            // Left
            if(column > 0) {
                int left = row * n + (column - 1);
                if(!stack.contains(left)) {
                    stack.add(left);
                    minWaterLevel = Math.min(minWaterLevel,
                            swimInWater(grid, m, n, row, column - 1, Math.max(largestWaterLevel, grid[row][column]), stack));
                    stack.remove(left);
                }
            }

            // Right
            if(column < n - 1) {
                int right = row * n + (column + 1);
                if(!stack.contains(right)) {
                    stack.add(right);
                    minWaterLevel = Math.min(minWaterLevel,
                            swimInWater(grid, m, n, row, column + 1, Math.max(largestWaterLevel, grid[row][column]), stack));
                    stack.remove(right);
                }
            }

            // Top
            if(row > 0) {
                int top = (row - 1) * n + column;
                if(!stack.contains(top)) {
                    stack.add(top);
                    minWaterLevel = Math.min(minWaterLevel,
                            swimInWater(grid, m, n, row - 1, column, Math.max(largestWaterLevel, grid[row][column]), stack));
                    stack.remove(top);
                }
            }

            // Bottom
            if(row < m - 1) {
                int bottom = (row + 1) * n + column;
                if(!stack.contains(bottom)) {
                    stack.add(bottom);
                    minWaterLevel = Math.min(minWaterLevel,
                            swimInWater(grid, m, n, row + 1, column, Math.max(largestWaterLevel, grid[row][column]), stack));
                    stack.remove(bottom);
                }
            }

            return minWaterLevel;
        }
    }

// [[0,2],[1,3]]
// [[29,28,12,2,24,11],[17,30,25,9,13,33],[1,0,34,35,23,19],[31,22,4,26,6,3],[21,14,15,8,32,20],[5,18,7,27,16,10]]
// [[31,28,33,0,8,57,86,99,23,98],[25,90,20,73,34,65,29,9,42,46],[17,84,10,4,40,5,41,21,71,79],[13,70,69,81,63,93,77,1,94,53],[38,87,61,50,92,2,15,95,82,68],[44,72,88,47,27,91,37,48,83,16],[3,30,96,66,7,58,76,54,19,64],[85,45,60,11,51,26,6,22,74,32],[43,12,62,59,89,52,36,97,49,78],[75,24,14,67,56,35,55,39,80,18]]
}

//    778. Swim in Rising Water
//    Hard
//    On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).
//
//    Now rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distance in zero time. Of course, you must stay within the boundaries of the grid during your swim.
//
//    You start at the top left square (0, 0). What is the least time until you can reach the bottom right square (N-1, N-1)?
//
//    Example 1:
//
//    Input: [[0,2],[1,3]]
//    Output: 3
//    Explanation:
//    At time 0, you are in grid location (0, 0).
//    You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
//
//    You cannot reach point (1, 1) until time 3.
//    When the depth of water is 3, we can swim anywhere inside the grid.
//    Example 2:
//
//    Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
//    Output: 16
//    Explanation:
//    0  1  2  3  4
//    24 23 22 21  5
//    12 13 14 15 16
//    11 17 18 19 20
//    10  9  8  7  6
//
//    The final route is marked in bold.
//    We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
//    Note:
//
//    2 <= N <= 50.
//    grid[i][j] is a permutation of [0, ..., N*N - 1].