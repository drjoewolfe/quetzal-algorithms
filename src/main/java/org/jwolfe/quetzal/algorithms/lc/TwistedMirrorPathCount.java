package org.jwolfe.quetzal.algorithms.lc;

public class TwistedMirrorPathCount {
    class Solution {

        private int MOD = 1_000_000_007;

        public int uniquePaths(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            Integer[][][] memo = new Integer[m][n][2];

            return uniquePaths(grid, m, n, 0, 0, 0, memo);
        }

        private int uniquePaths(int[][] grid, int m, int n, int r, int c, int fromLeft, Integer[][][] memo) {
            if (r == m - 1 && c == n - 1) {
                return 1;
            }

            if (r < 0 || r == m || c < 0 || c == n) {
                return 0;
            }

            if (memo[r][c][fromLeft] != null) {
                return memo[r][c][fromLeft];
            }

            int ways = 0;
            if (grid[r][c] == 0) {
                ways += uniquePaths(grid, m, n, r + 1, c, 0, memo);
                ways %= MOD;

                ways += uniquePaths(grid, m, n, r, c + 1, 1, memo);
                ways %= MOD;
            } else {
                if (fromLeft == 1) {
                    ways += uniquePaths(grid, m, n, r + 1, c, 0, memo);
                    ways %= MOD;
                } else {
                    ways += uniquePaths(grid, m, n, r, c + 1, 1, memo);
                    ways %= MOD;
                }
            }

            return memo[r][c][fromLeft] = ways;
        }
    }

    class Solution_Correct_1 {

        private int MOD = 1_000_000_007;

        public int uniquePaths(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;

            return uniquePaths(grid, m, n, 0, 0, false);
        }

        private int uniquePaths(int[][] grid, int m, int n, int r, int c, boolean fromLeft) {
            if (r == m - 1 && c == n - 1) {
                return 1;
            }

            if (r < 0 || r == m || c < 0 || c == n) {
                return 0;
            }

            int ways = 0;
            if (grid[r][c] == 0) {
                ways += uniquePaths(grid, m, n, r + 1, c, false);
                ways %= MOD;

                ways += uniquePaths(grid, m, n, r, c + 1, true);
                ways %= MOD;
            } else {
                if (fromLeft) {
                    ways += uniquePaths(grid, m, n, r + 1, c, false);
                    ways %= MOD;
                } else {
                    ways += uniquePaths(grid, m, n, r, c + 1, true);
                    ways %= MOD;
                }
            }

            return ways;
        }
    }
}

//    3665. Twisted Mirror Path Count
//    Medium
//    Given an m x n binary grid grid where:
//
//    grid[i][j] == 0 represents an empty cell, and
//    grid[i][j] == 1 represents a mirror.
//    A robot starts at the top-left corner of the grid (0, 0) and wants to reach the bottom-right corner (m - 1, n - 1). It can move only right or down. If the robot attempts to move into a mirror cell, it is reflected before entering that cell:
//
//    If it tries to move right into a mirror, it is turned down and moved into the cell directly below the mirror.
//    If it tries to move down into a mirror, it is turned right and moved into the cell directly to the right of the mirror.
//    If this reflection would cause the robot to move outside the grid boundaries, the path is considered invalid and should not be counted.
//
//    Return the number of unique valid paths from (0, 0) to (m - 1, n - 1).
//
//    Since the answer may be very large, return it modulo 109 + 7.
//
//    Note: If a reflection moves the robot into a mirror cell, the robot is immediately reflected again based on the direction it used to enter that mirror: if it entered while moving right, it will be turned down; if it entered while moving down, it will be turned right. This process will continue until either the last cell is reached, the robot moves out of bounds or the robot moves to a non-mirror cell.
//
//
//
//    Example 1:
//
//    Input: grid = [[0,1,0],[0,0,1],[1,0,0]]
//
//    Output: 5
//
//    Explanation:
//
//    Number	Full Path
//    1	(0, 0) → (0, 1) [M] → (1, 1) → (1, 2) [M] → (2, 2)
//    2	(0, 0) → (0, 1) [M] → (1, 1) → (2, 1) → (2, 2)
//    3	(0, 0) → (1, 0) → (1, 1) → (1, 2) [M] → (2, 2)
//    4	(0, 0) → (1, 0) → (1, 1) → (2, 1) → (2, 2)
//    5	(0, 0) → (1, 0) → (2, 0) [M] → (2, 1) → (2, 2)
//    [M] indicates the robot attempted to enter a mirror cell and instead reflected.
//
//    Example 2:
//
//    Input: grid = [[0,0],[0,0]]
//
//    Output: 2
//
//    Explanation:
//
//    Number	Full Path
//    1	(0, 0) → (0, 1) → (1, 1)
//    2	(0, 0) → (1, 0) → (1, 1)
//    Example 3:
//
//    Input: grid = [[0,1,1],[1,1,0]]
//
//    Output: 1
//
//    Explanation:
//
//    Number	Full Path
//    1	(0, 0) → (0, 1) [M] → (1, 1) [M] → (1, 2)
//    (0, 0) → (1, 0) [M] → (1, 1) [M] → (2, 1) goes out of bounds, so it is invalid.
//
//
//    Constraints:
//
//    m == grid.length
//    n == grid[i].length
//    2 <= m, n <= 500
//    grid[i][j] is either 0 or 1.
//    grid[0][0] == grid[m - 1][n - 1] == 0
