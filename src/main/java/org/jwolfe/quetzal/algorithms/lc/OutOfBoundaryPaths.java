package org.jwolfe.quetzal.algorithms.lc;

import java.util.Arrays;

public class OutOfBoundaryPaths {
    class Solution {
        private int[][] directions = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        int MOD = 1_000_000_007;

        public int findPaths(int m, int n, int N, int i, int j) {
            if(m < 1 || n < 1 || N < 1 || i < 0 || i >= m || j < 0 || j >= n) {
                return 0;
            }

            int count = m * n;
            int[][] memo = new int[count][N + 1];
            for(var row : memo) {
                Arrays.fill(row, -1);
            }

            return computeOutOfBoundaryPaths(m, n, i, j, N, memo);
        }

        private int computeOutOfBoundaryPaths(int m, int n, int row, int column, int movesLeft, int[][] memo) {
            if(row < 0 || row == m || column < 0 || column == n) {
                return 1;
            }

            if(movesLeft == 0) {
                return 0;
            }

            int cell = row * n + column;
            if(memo[cell][movesLeft] != -1) {
                return memo[cell][movesLeft];
            }

            int pathCount = 0;
            for(int[] direction : directions) {
                int r = row + direction[0];
                int c = column + direction[1];

                pathCount += computeOutOfBoundaryPaths(m, n, r, c, movesLeft - 1, memo);
                pathCount %= MOD;
            }

            memo[cell][movesLeft] = pathCount;
            return pathCount;
        }
    }

    class Solution_Recursive {
        private int[][] directions = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        int MOD = 1_000_000_007;

        public int findPaths(int m, int n, int N, int i, int j) {
            if(m < 1 || n < 1 || N < 1 || i < 0 || i >= m || j < 0 || j >= n) {
                return 0;
            }

            return computeOutOfBoundaryPaths(m, n, i, j, N);
        }

        private int computeOutOfBoundaryPaths(int m, int n, int row, int column, int movesLeft) {
            if(row < 0 || row == m || column < 0 || column == n) {
                return 1;
            }

            if(movesLeft == 0) {
                return 0;
            }

            int pathCount = 0;
            for(int[] direction : directions) {
                int r = row + direction[0];
                int c = column + direction[1];

                pathCount += computeOutOfBoundaryPaths(m, n, r, c, movesLeft - 1);
                pathCount %= MOD;
            }

            return pathCount;
        }
    }

// 2
// 2
// 2
// 0
// 0

// 1
// 3
// 3
// 0
// 1

// 8
// 7
// 16
// 1
// 5
}

//    576. Out of Boundary Paths
//    Medium
//    There is an m x n grid with a ball. The ball is initially at the position [startRow, startColumn]. You are allowed to move the ball to one of the four adjacent four cells in the grid (possibly out of the grid crossing the grid boundary). You can apply at most maxMove moves to the ball.
//
//    Given the five integers m, n, maxMove, startRow, startColumn, return the number of paths to move the ball out of the grid boundary. Since the answer can be very large, return it modulo 109 + 7.
//
//
//
//    Example 1:
//
//
//    Input: m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
//    Output: 6
//    Example 2:
//
//
//    Input: m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
//    Output: 12
//
//
//    Constraints:
//
//    1 <= m, n <= 50
//    0 <= maxMove <= 50
//    0 <= startRow <= m
//    0 <= startColumn <= n